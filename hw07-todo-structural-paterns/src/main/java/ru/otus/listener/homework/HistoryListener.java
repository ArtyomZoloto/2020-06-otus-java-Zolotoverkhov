package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;
import ru.otus.storage.HistoryStorage;

import java.time.LocalDateTime;


public class HistoryListener implements Listener {

    private final HistoryStorage storage;

    public HistoryListener(HistoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        storage.addRecord(new HistoryRecord(
                oldMsg.toBuilder().build(), //ummutability
                newMsg.toBuilder().build(),
                LocalDateTime.now())
        );
    }

    public static class HistoryRecord implements Comparable {
        private final LocalDateTime timeStamp;
        private final Message oldMsg;
        private final Message newMsg;

        public HistoryRecord(Message oldMsg, Message newMsg, LocalDateTime timeStamp) {
            this.oldMsg = oldMsg;
            this.newMsg = newMsg;
            this.timeStamp = timeStamp;
        }

        public LocalDateTime getTimeStamp() {
            return timeStamp;
        }

        public Message getOldMsg() {
            return oldMsg;
        }

        public Message getNewMsg() {
            return newMsg;
        }

        @Override
        public String toString() {
            return "HistoryRecord{" +
                    "timeStamp=" + timeStamp +
                    ", oldMsg=" + oldMsg +
                    ", newMsg=" + newMsg +
                    '}';
        }

        // для сравнения записей по дате
        @Override
        public int compareTo(Object o) {
            if (o == null) {
                return 0;
            }
            if (!(o instanceof HistoryRecord)){
                return 0;
            }
            return timeStamp.compareTo(((HistoryRecord) o).getTimeStamp());
        }

    }
}
