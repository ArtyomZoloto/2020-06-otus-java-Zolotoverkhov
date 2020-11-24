package ru.otus.processor.homework;

import ru.otus.EvenSecondException;
import ru.otus.Message;
import ru.otus.util.TimeProvider;
import ru.otus.processor.Processor;


/**
 * Processor should throw an exception if current second is even.
 */

public class ExceptionThrowingProcessor implements Processor {

    private TimeProvider timeProvider;

    public ExceptionThrowingProcessor(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public Message process(Message message) {
        if (timeProvider.isEvenSecond()) {
            throw new EvenSecondException("Even second, throwing exception!");
        }
        return message.toBuilder().build();
    }
}
