package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import ru.otus.EvenSecondException;
import ru.otus.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


class ExceptionThrowingProcessorTest {

    Processor processor = new ExceptionThrowingProcessor();

    @Test
    @DisplayName("Не должен выкинуть ошибку, т.к секунда четная")
    void processWithoutException() {
        Message message = new Message.Builder().field1("field1").build();
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        try (MockedStatic mocked = mockStatic(LocalDateTime.class)) {
            mocked.when(LocalDateTime::now).thenReturn(time);
            assertEquals(message, processor.process(message));
            mocked.verify(LocalDateTime::now);
        }
    }

    @Test
    @DisplayName("Должен выкинуть ошибку, т.к секунда НЕ четная")
    void processWithException() {
        assertThrows(EvenSecondException.class, () -> {
            Message message = new Message.Builder().field1("field1").build();
            LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 1, 2);
            try (MockedStatic mocked = mockStatic(LocalDateTime.class)) {
                mocked.when(LocalDateTime::now).thenReturn(time);
                assertEquals(message, processor.process(message));
                mocked.verify(LocalDateTime::now);
            }
        });
    }
}