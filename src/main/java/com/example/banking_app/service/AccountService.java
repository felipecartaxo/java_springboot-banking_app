package com.example.banking_app.service;

import com.example.banking_app.entities.Account;
import com.example.banking_app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Diz para o Spring que esta é a classe que contém a lógica principal das regras de negócio da nossa aplicação
public class AccountService {

    @Autowired // Mecanismo de injeção de dependência do Spring. Associa uma instância de AccountRepository na classe AccountService
    public AccountRepository accountRepository; // Injeção de dependência

    // Salva uma conta
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}