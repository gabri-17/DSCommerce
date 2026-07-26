package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// Implementar algumas regras de negócio relacionadas à controle de acesso.
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){
        User me = userService.authenticated(); // Pegar o usuário que está logado.
//        Testar se o usuário me não é "ADMIN" e também não é o mesmo usuário do userId enviado como parâmetro.
        if (!me.getId().equals(userId) && !me.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acess denied");
        }
    }
}
