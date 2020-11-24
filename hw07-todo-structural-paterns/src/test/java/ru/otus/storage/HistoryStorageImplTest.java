package ru.otus.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.listener.homework.HistoryListener;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class HistoryStorageImplTest {

    private HistoryStorage storage;

    @BeforeEach
    void setUp() {
        storage = new HistoryStorageImpl();
    }

    @Test
    @DisplayName("Должен записывать в storage данные")
    void addRecord() {
        storage.addRecord(new HistoryListener.HistoryRecord(null, null, LocalDateTime.now()));
        assertThat(storage.getAllRecords()).size().isOne();

        storage.addRecord(new HistoryListener.HistoryRecord(null, null, LocalDateTime.now()));
        assertThat(storage.getAllRecords()).size().isEqualTo(2);
    }

    @Test
    @DisplayName("Должен записать и считать данные из storage")
    void getAllRecords() {
        Message old1 = new Message.Builder().build();
        Message new1 = new Message.Builder().build();
        storage.addRecord(new HistoryListener.HistoryRecord(old1, new1, LocalDateTime.now()));
        assertThat(storage.getAllRecords()).size().isOne();
        assertThat(storage.getAllRecords().get(0).getOldMsg()).isEqualTo(old1);
        assertThat(storage.getAllRecords().get(0).getNewMsg()).isEqualTo(new1);

        Message old2 = new Message.Builder().build();
        Message new2 = new Message.Builder().build();
        storage.addRecord(new HistoryListener.HistoryRecord(old2, new2, LocalDateTime.now()));
        assertThat(storage.getAllRecords()).size().isEqualTo(2);
        assertThat(storage.getAllRecords().get(1).getOldMsg()).isEqualTo(old2);
        assertThat(storage.getAllRecords().get(1).getNewMsg()).isEqualTo(new2);
    }
}