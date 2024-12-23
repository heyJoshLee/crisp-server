package com.example.crisp_test.mvp.repository;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
//    @Query("SELECT a FROM Account a " +
//            "JOIN FETCH a.accountType " +
//            "WHERE a.id = :id")
//    Optional<Account> findAccountWithDetailsById(@Param("id") int id);
    @Query("SELECT e FROM Entry e WHERE e.account.id = :accountId")
    List<Entry> getEntriesForAccount(int accountId);
}
