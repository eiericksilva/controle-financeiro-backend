package com.eiericksilva.controle_financeiro.dto.mapper;

import org.springframework.stereotype.Component;
import com.eiericksilva.controle_financeiro.dto.AccountDTO;
import com.eiericksilva.controle_financeiro.entities.Account;

@Component
public class AccountMapper {

    public AccountDTO toDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getName(),
                account.getBalance(),
                account.getUser());
    }

    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.id());
        account.setName(accountDTO.name());
        account.setBalance(accountDTO.balance());
        account.setUser(accountDTO.user());

        return account;
    }
}