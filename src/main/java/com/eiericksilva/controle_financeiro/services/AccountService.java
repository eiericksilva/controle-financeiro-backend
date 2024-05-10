package com.eiericksilva.controle_financeiro.services;

import com.eiericksilva.controle_financeiro.dto.AccountDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.AccountMapper;
import com.eiericksilva.controle_financeiro.dto.mapper.UserMapper;
import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.User;
import com.eiericksilva.controle_financeiro.exceptions.InsufficientBalanceException;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.AccountRepository;
import com.eiericksilva.controle_financeiro.utils.VerifyNullPropertyNames;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    /*CREATE*/
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        return accountMapper.toDTO(accountRepository.save(account));
    }


    public AccountDTO createAccount(AccountDTO accountDTO) {
        Long userId = accountDTO.user().getId();
        User userFound = userMapper.toEntity(userService.findUserById(userId));

        Account account = accountMapper.toEntity(accountDTO);

        account.setUser(userFound);

        return accountMapper.toDTO(accountRepository.save(account));
    }

    /*READ*/
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

    /*UPDATE*/
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Account accountFound = accountMapper.toEntity(findAccountById(accountId));

        accountFound.setName(accountDTO.name());
        accountFound.setBalance(accountDTO.balance());
        accountFound.setUser(accountDTO.user());

        Account updatedAccount = accountRepository.save(accountFound);

        return accountMapper.toDTO(updatedAccount);
    }

    /*DELETE*/
    public void deleteAccount(Long accountId) {
        accountRepository.delete(accountMapper.toEntity(findAccountById(accountId)));
    }


    public void checkSufficientBalance(Account sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
    }


}
