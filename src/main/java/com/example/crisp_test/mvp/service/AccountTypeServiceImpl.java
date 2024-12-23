package com.example.crisp_test.mvp.service;
import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.exception.ResourceNotFoundException;
import com.example.crisp_test.mvp.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType getAccountTypeById(int id) {
        return findAccountTypeOrThrow(id);
    }

    @Override
    public AccountType createAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public void deleteAccountTypeById(int id) {
        accountTypeRepository.deleteById(id);
    }

    @Override
    public AccountType updateAccountTypeById(int id, AccountType accountTypeParams) {
        AccountType accountType = findAccountTypeOrThrow(id);
        accountType.setType(accountTypeParams.getType());
        return accountTypeRepository.save(accountType);
    }

    @Override
    public AccountType findAccountTypeOrThrow(int id) {
        return accountTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account type not found with id: " + id));
    }

    @Override
    public Map<String, Object> getAccountTypeWithAccounts(int id) {
        AccountType account = findAccountTypeOrThrow(id);
        List<Account> accounts = accountTypeRepository.getAccountsForAccountType(id);
        Map<String, Object> accountTypeWithAccounts = new HashMap<>();
        accountTypeWithAccounts.put("accounts", accounts);
        accountTypeWithAccounts.put("accountType", account);
        return accountTypeWithAccounts;
    }
}
