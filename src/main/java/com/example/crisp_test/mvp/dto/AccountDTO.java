package com.example.crisp_test.mvp.dto;

import java.util.Map;

public class AccountDTO {
    private Integer id;
    private String name;
    private String accountTypeName;
    private int accountTypeId;
    private Map<String, Integer> entries;
    private String description;

    public AccountDTO(Integer id, String name, String accountTypeName, int accountTypeId, Map<String, Integer> entries, String description) {
        this.id = id;
        this.name = name;
        this.accountTypeName = accountTypeName;
        this.accountTypeId = accountTypeId;
        this.entries = entries;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public Map<String, Integer> getEntries() {
        return entries;
    }

    public String getDescription() {
        return description;
    }
}
