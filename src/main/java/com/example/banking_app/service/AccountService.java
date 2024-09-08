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

    // Retorna uma conta a partir de um id
    public Optional<Account> getAccount(Long id) {
        // Considere lançar uma exceção caso a conta não seja encontrada

        return accountRepository.findById(id);
    }

    // Realiza o depósito em uma conta. Aqui iremos passar o id da conta que receberá o depósito e o valor do depósito
    public Account deposit(Long id, double amount) {
        // Busca a conta associada ao id acima e verifica se o Optional<Account> contém um valor. Se não tiver, lança uma exceção
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        // Atualiza o saldo da conta
        account.setBalance(account.getBalance() + amount);

        // Salva a conta atualizada no banco de dados e, após ser salvo no banco, retorna como resultado do método
        return accountRepository.save(account);
    }

    // Realiza o saque em uma conta. De forma análoga ao método acima, aqui iremos passar também o id da conta onde realizaremos o saque e o valor do saque
    public Account withdraw(Long id, double amount) {
        // Busca a conta associada ao id passado como parâmetro do método e verifica se o Optional<Account> contém um valor. Se não contiver, lança uma exceção
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        // Verifica se a conta possui saldo suficiente para a realização do saque
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        // Caso possua saldo suficiente, então atualiza o saldo da conta
        account.setBalance(account.getBalance() - amount);

        // Salva a conta atualizada no banco de dados e, após ser salvo no banco, retorna como resultado do método
        return accountRepository.save(account);
    }
}