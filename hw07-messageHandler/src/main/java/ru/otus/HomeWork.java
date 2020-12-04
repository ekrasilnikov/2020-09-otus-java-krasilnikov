package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.homework.ListenerMessageHistory;
import ru.otus.listener.ListenerPrinter;
import ru.otus.listener.homework.MessageHistory;
import ru.otus.listener.homework.MessageHistoryStorage;
import ru.otus.processor.*;
import ru.otus.processor.homework.ProcessorGenExEvenSec;
import ru.otus.processor.homework.ProcessorSwapFields;

import java.util.List;

public class HomeWork {
    public static void main(String[] args) {
        var processors = List.of(new LoggerProcessor(new ProcessorConcatFields()), new ProcessorSwapFields(),
                new ProcessorGenExEvenSec());
        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        var listenerPrinter = new ListenerPrinter();
        complexProcessor.addListener(listenerPrinter);

        var listenerKeepHistory = new ListenerMessageHistory();
        complexProcessor.addListener(listenerKeepHistory);

        var message1 = new Message.Builder(1L)
                .field1("f1")
                .field2("f2")
                .field3("f3")
                .field4("f4")
                .field5("f5")
                .field6("f6")
                .field7("f7")
                .field8("f8")
                .field9("f9")
                .field10("f10")
                .field11("f11")
                .field12("f12")
                .build();

        var message2 = new Message.Builder(1L)
                .field1("x1")
                .field2("x2")
                .field3("x3")
                .field4("x4")
                .field10("x10")
                .field12("x12")
                .build();

        var message3 = new Message.Builder(1L)
                .field4("a4")
                .field10("a10")
                .build();

        complexProcessor.handle(message1);
        complexProcessor.handle(message2);
        complexProcessor.handle(message3);

        System.out.println("-------Message History---------");
        for (MessageHistory current : MessageHistoryStorage.getInstance().getMessageHistoryList()) {
            System.out.println(current);
        }
    }
}
