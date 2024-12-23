package com.example.crisp_test.mvp.controller;

import com.example.crisp_test.mvp.dto.AccountDTO;
import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(@Qualifier("accountServiceImpl") AccountService accountService) {
        this.accountService = accountService;
    }

    // Index
    @GetMapping("/")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    // Create
    @PostMapping("/")
    public Account createAccount(@RequestBody AccountDTO accountParams) {
        System.out.println("AccountTypeid: " + accountParams.getAccountTypeId());
        Account newAccount = new Account();
        AccountType accountType = new AccountType();
        accountType.setId(accountParams.getAccountTypeId());
        newAccount.setAccountType(accountType);
        newAccount.setName(accountParams.getName());
        newAccount.setDescription(accountParams.getDescription());
        return accountService.createAccount(newAccount);
    }

    // Read
    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable int accountId) {
        return accountService.findAccountOrThrow(accountId);
    }

    // Update
    @PatchMapping("/{accountId}")
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account accountParams) {
        return accountService.updateAccountById(accountId, accountParams);
    }

    // Delete
    @DeleteMapping("/{accountId}")
    public void deleteAccountById(@PathVariable int accountId) {
        accountService.deleteAccountById(accountId);
    }

    @GetMapping("/{accountId}/entries")
    public List<Entry> getAccountEntries(@PathVariable int accountId) {
        return accountService.getEntriesForAcount(accountId);
    }

    @GetMapping("{accountId}/balance")
    public int getAccountTotal(@PathVariable int accountId) {
        return accountService.getAccountBalance(accountId);
    }



    // With details
//    @GetMapping("/{accountId}/details")
//    public AccountDTO getAccountDetailsById(@PathVariable int accountId) {
//        return accountService.findAccountWithDetailsById(accountId);
//    }
}
