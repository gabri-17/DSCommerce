package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.*;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
//        Mandar buscar o meu Order no banco de dados
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order); // Retornao objeto Order convertendo para DTO.
    }

    @Transactional
    public @Valid OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now()); // Criar um instante com a data (time-stamp) atual (instante atual)
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

//        Varrer todos os itens que vieram na minha requisição (no body dela) e chamando cada um pelo apelido itemDto.
        for (OrderItemDTO itemDto : dto.getItems()) {
//            Montar dentro da memória toda a estrutura de objeto.
            Product product = productRepository.getReferenceById(itemDto.getProductId());

//            Copiar o preco do produto para manter o histórico do preco que foi vendido no período.
            OrderItem item = new OrderItem(order,product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item); // O pedido está associado com os itens da mesma forma que cada item está
//            associado com o pedido e o produto também.
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
