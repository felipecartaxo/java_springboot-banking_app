package com.example.banking_app.service;

import com.example.banking_app.entities.Account;
import com.example.banking_app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Diz para o Spring que esta é a classe que contém a lógica principal das regras de negócio da nossa aplicação
public class AccountService {

    @Autowired // Mecanismo de injeção de dependência do Spring. Associa uma instância de AccountRepository na classe AccountService
    public AccountRepository repository; // Injeção de dependência

    // Cria e salva uma conta
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    // Retorna todas as contas registradas
    public List<Account> findAll() {
        return repository.findAll();
    }

    // Retorna uma conta a partir de um id
    public Optional<Account> getAccount(Long id) {
        return repository.findById(id);
    }

    // Realiza o depósito em uma conta. Aqui iremos passar o id da conta que receberá o depósito e o valor do depósito
    public Account deposit(Long id, double amount) {
        Account account = getAccount(id) // Busca a conta associada ao id acima e verifica se o Optional<Account> contém um valor
                .orElseThrow(() -> new RuntimeException("Account not found")); // Se não tiver, lança uma exceção

        account.setBalance(account.getBalance() + amount); // Atualiza o saldo da conta

        return repository.save(account); // Salva a conta atualizada no banco de dados e, após ser salvo no banco, retorna como resultado do método
    }

    // Realiza o saque em uma conta. De forma análoga ao método acima, aqui iremos passar também o id da conta onde realizaremos o saque e o valor do saque
    public Account withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found")); // Busca a conta associada ao id passado como parâmetro do método e verifica se o Optional<Account> contém um valor. Se não contiver, lança uma exceção
        if (account.getBalance() < amount) { // Verifica se a conta possui saldo suficiente para a realização do saque
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount); // Caso possua saldo suficiente, então atualiza o saldo da conta

        return repository.save(account); // Após a operação ser concluída, a conta será salva com seu saldo atualizado
    }

    // Delete uma conta a partir de um id
    public void delete(Long id) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));

        repository.deleteById(account.getId());
    }

    public Account update(Long id, Account obj) {
        Account entity = repository.getReferenceById(id);
        entity.setAccountHolderName(obj.getAccountHolderName());

        return repository.save(entity);
    }
}