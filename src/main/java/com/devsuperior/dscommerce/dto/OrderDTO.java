package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity){
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient()); // Vai ser um DTO usando o construtor recebendo o usuário.
        payment = entity.getPayment() != null ? new PaymentDTO(entity.getPayment()) : null;
        for(OrderItem item : entity.getItems()) {
            items.add(new OrderItemDTO(item));
        }

    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public double getTotal(){
        double sum = 0.0;

//        Soma dos subtotais dos itens (items)
        for (OrderItemDTO item : items) {
//            Delegar a lógica de subtotal para o item e uso ela para calcular a soma total
            sum += item.getSubTotal();
        }
        return sum;
    }
}