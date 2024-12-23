package com.example.crisp_test.mvp.repository;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

    @Query("SELECT a FROM Entry a JOIN FETCH a.account WHERE a.id = :id")
    Optional<Entry> findEntryWithDetailsById(@Param("id") int id);
}
