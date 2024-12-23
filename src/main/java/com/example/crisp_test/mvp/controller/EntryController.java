package com.example.crisp_test.mvp.controller;

import com.example.crisp_test.mvp.dto.EntryDTO;
import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.service.AccountService;
import com.example.crisp_test.mvp.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entries")

public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(@Qualifier("entryServiceImpl") EntryService entryService) {
        this.entryService = entryService;
    }

    // Index
    @GetMapping("/")
    public List<Entry> getEntries() {
        return entryService.getAllEntries();
    }

    // Create
    @PostMapping("/")
    public Entry createEntry(@RequestBody EntryDTO entryParams) {
        Entry newEntry = new Entry();
        Account account = new Account();
        account.setId(entryParams.getAccountId());
        newEntry.setAccount(account);
        newEntry.setName(entryParams.getName());
        newEntry.setValue(entryParams.getValue());
        newEntry.setDate(entryParams.getDate());
        return entryService.createEntry(newEntry);
    }

    // Read
    @GetMapping("/{entryId}")
    public Entry getEntryById(@PathVariable int entryId) {
        return entryService.findEntryOrThrow(entryId);
    }

    // Update
    @PatchMapping("/{entryId}")
    public Entry updateEntry(@PathVariable int entryId, @RequestBody Entry entryParams) {
        return entryService.updateEntryById(entryId, entryParams);
    }

    // Delete
    @DeleteMapping("/{entryId}")
    public void deleteEntryById(@PathVariable int entryId) {
        entryService.deleteEntryById(entryId);
    }

    // With details
    @GetMapping("/{entryId}/details")
    public Entry getEntryDetailsById(@PathVariable int entryId) {
        return entryService.findEntryWithDetailsById(entryId);
    }
}
