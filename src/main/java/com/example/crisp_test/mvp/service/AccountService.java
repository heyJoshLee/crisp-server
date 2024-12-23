package com.example.crisp_test.mvp.service;

import com.example.crisp_test.mvp.dto.AccountDTO;
import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAllAccounts();
    Optional<Account> getAccountById(int id);
    Account createAccount(Account accountParams);
    void deleteAccountById(int id);
    Account updateAccountById(int id, Account accountParams);
    Account findAccountOrThrow(int id);
    List<Entry> getEntriesForAcount(int id);
    int getAccountBalance(int id);
//    AccountDTO findAccountWithDetailsById(int id);
}
