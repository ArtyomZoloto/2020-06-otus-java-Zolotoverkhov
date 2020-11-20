package ru.otus.listener.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class HistoryListenerTest {

    private Map<LocalDateTime, HistoryListener.HistoryRecord> storage;

    @BeforeEach
    void setUp() {
        storage = new HashMap<>();
    }

    @Test
    void onUpdated() {
        var historyListener = new HistoryListener(storage);
        historyListener.onUpdated(new Message.Builder().build(), new Message.Builder().build());
        assertThat(storage).size().isEqualTo(1);
        historyListener.onUpdated(new Message.Builder().build(), new Message.Builder().build());
        assertThat(storage).size().isEqualTo(2);
    }
}