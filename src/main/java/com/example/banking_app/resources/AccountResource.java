package com.example.banking_app.resources;

import com.example.banking_app.entities.Account;
import com.example.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // Indica que esta classe é um REST Controller
@RequestMapping("/api/accounts") // Define que todos os endpoints definidos nesta classe serão acessados a partir de /api/accounts
public class AccountResource { // Classe que disponibiliza as regras de negócio como endpoints de uma API RESTful

    @Autowired // Injeta automaticamente a dependência do serviço AccountService, que contém a lógica das regras de negócio
    private AccountService accountService;

    @PostMapping // Este método será chamado quando uma requisição HTTP do tipo POST for feita para /api/accounts. É utilizado para criar uma nova conta
    public Account createAccount(@RequestBody Account account) { // Determina que o corpo da requisição será transformado em uma instância da classe Account e passado para o método
        return accountService.createAccount(account); // Retorna a conta criada
    }

    @GetMapping("/{id}") // Responde a requisições GET feitas para /api/accounts/{id}. Serve para obter os detalhes de uma conta a partir de um id
    public Account getAccount(@PathVariable Long id) { // O id da conta é extraído da URL e passado como argumento
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found")); // Busca a conta correspondente ao id fornecido. Se a conta não for encontrada, uma exceção RuntimeException
    }

    @PostMapping("{id}/deposit") // Responde a requisições POST para /api/accounts/{id}/deposit. Usado para realizar depósitos em uma conta específica
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) { // Extrai o id da conta a partir da URL. O corpo da requisição contém um Map de chave-valor, onde a chave é "amount" e o valor é o próprio valor do depósito
        Double amount = request.get("amount"); // O método extrai o valor associado à chave "amount" no request body

        return accountService.deposit(id, amount); // Chama o serviço accountService.deposit(id, amount), que executa a lógica do depósito. Após a operação, a conta atualizada é retornada
    }

    @PostMapping("{id}/withdraw") // @PostMapping("{id}/withdraw"): Responde a requisições POST para /api/accounts/{id}/withdraw. Usado para realizar saques em uma conta específica
    public Account withdraw (@PathVariable Long id, @RequestBody Map<String, Double> request) { // Extrai o id da conta a partir da URL. O corpo da requisição contém um Map de chave-valor, onde a chave é "amount" e o valor é o próprio valor do saque
        Double amount = request.get("amount"); // O método extrai o valor associado à chave "amount" no request body

        return accountService.withdraw(id, amount); // Chama o serviço accountService.withdraw(id, amount), que executa a lógica do saque. Após a operação, a conta atualizada é retornada
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        accountService.delete(id);

        return ResponseEntity.noContent().build();
    }
}