package com.example.banking_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter // Faz com que o Lombok gere os getters
@Setter // Faz com que o Lombok gere os setters
@NoArgsConstructor // Faz com que o Lombok gere um construtor padrão sem argumentos
@Entity // Determina que a classe abaixo é uma entidade no nosso banco
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolderName;
    private double balance;
}