package ru.otus.storage;

import ru.otus.listener.homework.HistoryListener.HistoryRecord;

import java.util.*;

public class HistoryStorageImpl implements HistoryStorage {

    private final List<HistoryRecord> storage = new ArrayList<>();


    @Override
    public void addRecord(HistoryRecord record) {
        storage.add(record);
    }

    @Override
    public List<HistoryRecord> getAllRecords() {
        return Collections.unmodifiableList(storage);
    }
}
