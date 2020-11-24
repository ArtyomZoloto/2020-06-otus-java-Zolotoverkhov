package ru.otus.storage;

import ru.otus.listener.homework.HistoryListener.HistoryRecord;

import java.util.List;


public interface HistoryStorage {

    /**
     * Add record to storage
     * @param record HistoryRecord instance
     */
    void addRecord(HistoryRecord record);

    /**
     * Returns all records
     * @return list with records
     */
    List<HistoryRecord> getAllRecords();
}
