package com.example.crisp_test.mvp.repository;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    @Query("SELECT a FROM Account a WHERE a.accountType.id = :accountTypeId")
    List<Account> getAccountsForAccountType(@Param("accountTypeId") int id);
}
