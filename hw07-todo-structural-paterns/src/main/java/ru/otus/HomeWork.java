package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.storage.HistoryStorageImpl;
import ru.otus.processor.homework.SwitchProcessor;
import ru.otus.util.TimeProvider;
import ru.otus.util.TimeProviderImpl;
import ru.otus.listener.ListenerPrinter;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.processor.LoggerProcessor;
import ru.otus.processor.ProcessorConcatFields;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.processor.homework.ExceptionThrowingProcessor;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        TimeProvider timeProvider = new TimeProviderImpl();
        var processors = List.of(
                new SwitchProcessor(),
                new ExceptionThrowingProcessor(timeProvider),
                new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10())
        );

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});
        var listenerPrinter = new ListenerPrinter();
        var historyStorage = new HistoryStorageImpl();
        var historyListener = new HistoryListener(historyStorage);
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(historyListener);

        List<String> strings = new ArrayList<>() {{
            add("s1");
            add("s2");
        }};
        ObjectForMessage objectForMessage = new ObjectForMessage(strings);

        var message = new Message.Builder()
                .field11("field11")
                .field12("field12")
                .field13(objectForMessage)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("F13 history is: " + historyStorage.getAllRecords().get(0).getNewMsg().getField13().getData());
        strings.add("s3");
        System.out.println("F13 history is: " + historyStorage.getAllRecords().get(0).getNewMsg().getField13().getData());
        strings.add("s5");
        System.out.println("F13 history is: " + historyStorage.getAllRecords().get(0).getNewMsg().getField13().getData());

        complexProcessor.removeListener(listenerPrinter);
        complexProcessor.removeListener(historyListener);
    }
}
