package ru.otus.processor.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.processor.Processor;

import static org.assertj.core.api.Assertions.*;

class SwitchProcessorTest {

    private Processor processor;

    @BeforeEach
    void beforeEach() {
        processor = new SwitchProcessor();
    }

    @Test
    void processTest() {
        Message message = new Message.Builder().field11("field11").field12("field12").build();

        Message resultMessage = processor.process(message);

        assertThat(resultMessage.getField11()).isEqualTo("field12");
        assertThat(resultMessage.getField12()).isEqualTo("field11");
    }
}