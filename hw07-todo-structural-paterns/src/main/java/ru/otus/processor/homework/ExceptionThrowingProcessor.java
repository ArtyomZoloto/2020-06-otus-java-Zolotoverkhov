package ru.otus.processor.homework;

import ru.otus.EvenSecondException;
import ru.otus.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

/**
 * Processor should throw an exception if current second is even.
 */

public class ExceptionThrowingProcessor implements Processor {

    @Override
    public Message process(Message message) {
        if (LocalDateTime.now().getSecond() % 2 == 0) {
            throw new EvenSecondException("Even second, throwing exception!");
        }
        return message.toBuilder().build();
    }
}
