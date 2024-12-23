package com.example.crisp_test.mvp.service;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountTypeService {
    List<AccountType> getAllAccountTypes();
    AccountType getAccountTypeById(int id);
    AccountType createAccountType(AccountType accountType);
    void deleteAccountTypeById(int id);
    AccountType updateAccountTypeById(int id, AccountType accountType);
    AccountType findAccountTypeOrThrow(int id);
    Map<String, Object> getAccountTypeWithAccounts(int id);
}
