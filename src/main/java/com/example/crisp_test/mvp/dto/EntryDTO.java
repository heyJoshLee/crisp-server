package com.example.crisp_test.mvp.dto;

import com.example.crisp_test.mvp.entity.Account;
import jakarta.persistence.*;

import java.time.LocalDate;

public class EntryDTO {

    private Integer id;
    private String name;
    private Integer value;
    private LocalDate date;
    private Account account;
    private int accountId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public int getAccountId() {
        return accountId;
    }
}
