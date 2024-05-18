package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.eiericksilva.controle_financeiro.dto.AccountDTO;
import com.eiericksilva.controle_financeiro.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;


    /*CREATE*/
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AccountDTO createAccount(
            @Valid @RequestBody AccountDTO accountDTO) {

        return accountService.createAccount(accountDTO);
    }

    /*READ*/
    @GetMapping
    public List<AccountDTO> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/{id}")
    public AccountDTO findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    /*UPDATE*/
    @PutMapping("/{accountId}")
    public AccountDTO update(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountId, accountDTO);
    }

    /*DELETE*/
    @DeleteMapping("/{accountId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void deleteAccount(@PathVariable Long accountId) {

        accountService.deleteAccount(accountId);
    }


}
