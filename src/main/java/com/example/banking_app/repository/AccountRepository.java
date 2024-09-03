package com.example.banking_app.repository;

import com.example.banking_app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

// Classe responsável pelas operações de CRUD da classe Account
public interface AccountRepository extends JpaRepository<Account, Long> {

}