package com.example.crisp_test.mvp.controller;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("accountTypes")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeController(@Qualifier("accountTypeServiceImpl") AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    // Index
    @GetMapping("/")
    public List<AccountType> getAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }

    // Create
    @PostMapping("/")
    public AccountType createAccountType(@RequestBody AccountType accountTypeParams) {
        return accountTypeService.createAccountType(accountTypeParams);
    }

    // Read
    @GetMapping("/{accountTypeId}")
    public AccountType getAccountTypeById(@PathVariable("accountTypeId") int accountTypeId) {
        return accountTypeService.findAccountTypeOrThrow(accountTypeId);
    }

    // Update
    @PatchMapping("/{accountTypeId}")
    public AccountType updateAccountTypeById(@PathVariable("accountTypeId") int accountTypeId, @RequestBody AccountType accountTypeParams) {
        return accountTypeService.updateAccountTypeById(accountTypeId, accountTypeParams);
    }

    // Delete
    @DeleteMapping("{accountTypeId}")
    public void deleteAccountTypeById(@PathVariable("accountTypeId") int accountTypeId) {
        accountTypeService.deleteAccountTypeById(accountTypeId);
    }

    // AccountType with Accounts
    @GetMapping("{accountTypeId}/accounts")
    public Map<String, Object> getAccountTypeWithAccounts(@PathVariable("accountTypeId") int accountTypeId) {
        return accountTypeService.getAccountTypeWithAccounts(accountTypeId);
    }
}
