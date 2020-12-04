package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

import java.time.LocalTime;

public class ProcessorGenExEvenSec implements Processor {
    private final int checkSecond;

    @Override
    public Message process(Message message) {
        checkEven();
        return message;
    }

    public ProcessorGenExEvenSec() {
        checkSecond = LocalTime.now().toSecondOfDay();
    }

    public ProcessorGenExEvenSec(int checkSecond) {
        this.checkSecond = checkSecond;
    }

    private void checkEven() {
        if (checkSecond % 2 == 0) {
            System.out.println("Even Second!");
            throw new ProcessorGenExEvenSecException(checkSecond);
        }
    }

}
