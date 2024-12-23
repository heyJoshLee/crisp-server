package com.example.crisp_test.mvp.service;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryService {

    List<Entry> getAllEntries();
    Entry getEntryById(int id);
    Entry createEntry(Entry entryParams);
    void deleteEntryById(int id);
    Entry updateEntryById(int id, Entry entryParams);
    Entry findEntryOrThrow(int id);
    Entry findEntryWithDetailsById(int id);
}
