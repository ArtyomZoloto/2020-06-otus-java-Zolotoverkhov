package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.EvenSecondException;
import ru.otus.Message;
import ru.otus.util.TimeProvider;
import ru.otus.processor.Processor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExceptionThrowingProcessorTest {

    private final TimeProvider timeProviderMock = mock(TimeProvider.class);
    private final Processor processor = new ExceptionThrowingProcessor(timeProviderMock);

    @Test
    @DisplayName("Не должен выкинуть ошибку, т.к секунда четная")
    void processWithoutException() {
        Message message = new Message.Builder().field1("field1").build();
        when(timeProviderMock.isEvenSecond()).thenReturn(false);
        assertEquals(message, processor.process(message));
    }

    @Test
    @DisplayName("Не должен выкинуть ошибку, т.к секунда четная")
    void processWithException() {
        final Message message = new Message.Builder().field1("field1").build();
        when(timeProviderMock.isEvenSecond()).thenReturn(true);
        assertThrows(EvenSecondException.class, ()-> {
            processor.process(message);
        });
    }
}