package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eiericksilva.controle_financeiro.dto.AccountDTO;
import com.eiericksilva.controle_financeiro.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountDTO> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/{id}")
    public AccountDTO findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AccountDTO createAccount(
            @PathVariable Long userId,
            @Valid @RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(userId, accountDTO);
    }
}
