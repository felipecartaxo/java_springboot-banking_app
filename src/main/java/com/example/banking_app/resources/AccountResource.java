package com.example.banking_app.resources;

import com.example.banking_app.entities.Account;
import com.example.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Determina que esta classe vai lidar com requisições web
@RequestMapping("/api/accounts") // Determina que este será o prefixo dos endpoints abaixo
public class AccountResource { // Classe que disponibiliza as regras de negócio como endpoints de uma API RESTful

    @Autowired // Injetando a dependência da classe que contém as regras de negócio
    private AccountService accountService;

    @PostMapping // Método que será chamado ao realizar uma requisição do tipo POST no endpoint /api/accounts
    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }
}