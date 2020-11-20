package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;

import java.time.LocalDateTime;
import java.util.Map;

public class HistoryListener implements Listener {

    Map<LocalDateTime, HistoryRecord> storage;

    public HistoryListener(Map<LocalDateTime, HistoryRecord> storage) {
        this.storage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        storage.put(LocalDateTime.now(), new HistoryRecord(oldMsg, newMsg));
    }

    public static class HistoryRecord {
        Message oldMsg, newMsg;

        HistoryRecord(Message oldMsg, Message newMsg) {
            this.oldMsg = oldMsg;
            this.newMsg = newMsg;
        }

        @Override
        public String toString() {
            return "HistoryRecord{" +
                    "oldMsg=" + oldMsg +
                    ", newMsg=" + newMsg +
                    '}';
        }
    }
}
