package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

import java.time.LocalTime;

public class ProcessorGenExEvenSec implements Processor {
    private int checkSecond;

    @Override
    public Message process(Message message) {
        if (checkEven()) {
            throw new ProcessorGenExEvenSecException(checkSecond);
        }
        return message;
    }

    public boolean checkEven() {
        checkSecond = LocalTime.now().toSecondOfDay();
        return checkSecond % 2 == 0;
    }
}
