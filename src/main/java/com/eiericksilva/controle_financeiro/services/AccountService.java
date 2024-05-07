package com.eiericksilva.controle_financeiro.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiericksilva.controle_financeiro.dto.AccountDTO;
import com.eiericksilva.controle_financeiro.dto.UserDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.AccountMapper;
import com.eiericksilva.controle_financeiro.dto.mapper.UserMapper;
import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.User;
import com.eiericksilva.controle_financeiro.exceptions.InsufficientBalanceException;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.AccountRepository;

import jakarta.validation.Valid;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserService userService;

    public List<AccountDTO> findAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> accountMapper.toDTO(account))
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(Long id) {
        return accountMapper.toDTO(accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        return accountMapper.toDTO(accountRepository.save(account));
    }

    public AccountDTO createAccount(Long userId, @Valid AccountDTO accountDTO) {
        UserDTO userDTO = userService.findUserById(userId);
        User user = userMapper.toEntity(userDTO);
        Account account = accountMapper.toEntity(accountDTO);

        account.setUser(user);

        return accountMapper.toDTO(accountRepository.save(account));
    }

    public void checkSufficientBalance(Account sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
    }

}
