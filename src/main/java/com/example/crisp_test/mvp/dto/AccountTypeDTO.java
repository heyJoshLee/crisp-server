package com.example.crisp_test.mvp.dto;

import java.util.Map;

public class AccountTypeDTO {
    private Integer id;
    private String type;
    private String accountName;
    private Map<String, String> accounts;

    public AccountTypeDTO(Integer id, String type, String accountName) {
        this.id = id;
        this.type = type;
        this.accountName = accountName;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAccountName() {
        return accountName;
    }

    public Map<String, String> getAccounts() {
        return accounts;
    }
}
