package com.eiericksilva.controle_financeiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eiericksilva.controle_financeiro.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
