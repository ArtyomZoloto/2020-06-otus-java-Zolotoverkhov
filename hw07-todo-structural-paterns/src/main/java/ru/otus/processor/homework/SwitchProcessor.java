package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

/**
 * This processor should switch data between fiedl11 and field 12
 */
public class SwitchProcessor implements Processor {

    @Override
    public Message process(Message message) {

        return message.toBuilder()
                .field11(message.getField12())
                .field12(message.getField11())
                .build();
    }
}
