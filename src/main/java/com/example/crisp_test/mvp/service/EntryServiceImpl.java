package com.example.crisp_test.mvp.service;

import com.example.crisp_test.mvp.entity.Account;
import com.example.crisp_test.mvp.entity.AccountType;
import com.example.crisp_test.mvp.entity.Entry;
import com.example.crisp_test.mvp.exception.ResourceNotFoundException;
import com.example.crisp_test.mvp.repository.AccountTypeRepository;
import com.example.crisp_test.mvp.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry getEntryById(int id) {
        return findEntryOrThrow(id);
    }

    @Override
    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public void deleteEntryById(int id) {
        entryRepository.deleteById(id);
    }

    @Override
    public Entry updateEntryById(int id, Entry entryParams) {
        Entry entry = findEntryOrThrow(id);
        entry.setName(entryParams.getName());
        entry.setValue(entryParams.getValue());
        entry.setDate(entryParams.getDate());
        return entryRepository.save(entry);
    }

    @Override
    public Entry findEntryOrThrow(int id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found with id: " + id));
    }

    @Override
    public Entry findEntryWithDetailsById(int id) {
        return entryRepository.findEntryWithDetailsById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Entry not found with id: " + id));
    }

}
