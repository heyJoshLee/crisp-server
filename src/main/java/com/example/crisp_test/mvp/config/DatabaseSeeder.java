package com.example.crisp_test.mvp.config;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.repository.AccountRepository;
import com.example.crisp_test.mvp.repository.AccountTypeRepository;
import com.example.crisp_test.mvp.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            // Check if account types are already seeded
            if (accountTypeRepository.count() == 0) {
                // Seed account types and accounts
                seedAccountTypesAndAccounts();
            }
        };
    }

    private void seedAccountTypesAndAccounts() {
        AccountType checking = createAccountType("Accounts Payable");
        AccountType saving = createAccountType("Fixed Assets");

        accountTypeRepository.saveAll(Arrays.asList(checking, saving));
        seedAccounts(checking, saving);
    }

    private AccountType createAccountType(String type) {
        AccountType accountType = new AccountType();
        accountType.setType(type);
        return accountType;
    }

    private void seedAccounts(AccountType checking, AccountType saving) {
        Account account1 = createAccount("Alice", "Alice's account", checking);
        Account account2 = createAccount("Adam", "Adam's account", checking);
        Account account3 = createAccount("Bob", "Bob's account", saving);
        Account account4 = createAccount("Ben", "Ben's account", saving);

        accountRepository.saveAll(Arrays.asList(account1, account2, account3, account4));

        seedEntries(account1, account2, account3, account4);
    }

    private Account createAccount(String name, String description, AccountType accountType) {
        Account account = new Account();
        account.setName(name);
        account.setDescription(description);
        account.setAccountType(accountType);
        return account;
    }

    private void seedEntries(Account account1, Account account2, Account account3, Account account4) {
        List<Entry> entries = Arrays.asList(
                createEntry("direct deposit", 1500, LocalDate.of(2024, 1, 10), account1),  // Direct Deposit for Alice
                createEntry("Walmart", -50, LocalDate.of(2024, 1, 10), account1),
                createEntry("Target", -30, LocalDate.of(2024, 1, 15), account1),
                createEntry("Amazon", -100, LocalDate.of(2024, 2, 5), account1),

                createEntry("direct deposit", 2000, LocalDate.of(2024, 1, 12), account2),  // Direct Deposit for Adam
                createEntry("Whole Foods", -150, LocalDate.of(2024, 1, 12), account2),
                createEntry("Best Buy", -250, LocalDate.of(2024, 1, 20), account2),
                createEntry("Costco", -200, LocalDate.of(2024, 2, 1), account2),

                createEntry("direct deposit", 1200, LocalDate.of(2024, 1, 5), account3),  // Direct Deposit for Bob
                createEntry("Starbucks", -20, LocalDate.of(2024, 1, 5), account3),
                createEntry("Target", -50, LocalDate.of(2024, 1, 10), account3),
                createEntry("Etsy", -75, LocalDate.of(2024, 2, 3), account3),

                createEntry("direct deposit", 1800, LocalDate.of(2024, 1, 17), account4),  // Direct Deposit for Ben
                createEntry("Nike", -90, LocalDate.of(2024, 1, 17), account4),
                createEntry("Apple Store", -500, LocalDate.of(2024, 1, 22), account4),
                createEntry("Amazon", -120, LocalDate.of(2024, 2, 4), account4)
        );

        entryRepository.saveAll(entries);
    }

    private Entry createEntry(String storeName, int value, LocalDate date, Account account) {
        Entry entry = new Entry();
        entry.setName(storeName);
        entry.setValue(value);
        entry.setDate(date);
        entry.setAccount(account);
        return entry;
    }
}
