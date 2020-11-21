package ru.otus.listener.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.storage.HistoryStorage;
import ru.otus.storage.HistoryStorageImpl;

import static org.assertj.core.api.Assertions.*;

class HistoryListenerTest {

    private final HistoryStorage storage = new HistoryStorageImpl();

    @Test
    @DisplayName("Должен записывать в storage данные")
    void onUpdated() {
        var historyListener = new HistoryListener(storage);
        Message old1 = new Message.Builder().build();
        Message new1 = new Message.Builder().build();
        historyListener.onUpdated(old1, new1);
        assertThat(storage.getAllRecords()).size().isEqualTo(1);

        Message old2 = new Message.Builder().build();
        Message new2 = new Message.Builder().build();
        historyListener.onUpdated(old2, new2);
        assertThat(storage.getAllRecords()).size().isEqualTo(2);
    }
}