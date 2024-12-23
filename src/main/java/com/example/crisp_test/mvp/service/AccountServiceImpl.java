package com.example.crisp_test.mvp.service;

import com.example.crisp_test.mvp.dto.AccountDTO;
import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.repository.AccountRepository;
import com.example.crisp_test.mvp.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountTypeRepository accountTypeRepository;
    private AccountTypeService accountTypeService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountTypeRepository accountTypeRepository, AccountTypeService accountTypeService) {
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeService = accountTypeService;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccountById(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account updateAccountById(int id, Account accountParams) {
        Account account = findAccountOrThrow(id);
        if (accountParams.getAccountType() != null && accountParams.getAccountType().getId() != null) {
            AccountType accountType = accountTypeService.findAccountTypeOrThrow(accountParams.getAccountType().getId());
            account.setAccountType(accountType);
        }
        account.setName(accountParams.getName());
        account.setDescription(accountParams.getDescription());
        return accountRepository.save(account);
    }

    @Override
    public Account findAccountOrThrow(int id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }


    @Override
    public List<Entry> getEntriesForAcount(int id) {
        return accountRepository.getEntriesForAccount(id);
    }

    public int getAccountBalance(int accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getBalanceTotal();
    }

//    @Override
//    public AccountDTO findAccountWithDetailsById(int id) {
//        Account account = accountRepository.findAccountWithDetailsById(id)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        Map<String, Integer> entries = account.getEntries().stream()
//                .collect(Collectors.toMap(Entry::getName, Entry::getValue));
//
//        return new AccountDTO(
//                account.getId(),
//                account.getName(),
//                account.getAccountType().getType(),
//                account.getAccountType().getId(),
//                entries,
//                account.getDescription()
//        );
//    }
}
//