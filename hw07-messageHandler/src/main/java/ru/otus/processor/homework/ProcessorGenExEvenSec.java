package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

public class ProcessorGenExEvenSec implements Processor {
    private final DateTimeProvider dateTimeProvider;

    public ProcessorGenExEvenSec(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (checkEven()) {
            throw new ProcessorGenExEvenSecException(dateTimeProvider.getDate().getSecond());
        }
        return message;
    }

    public boolean checkEven() {
        int checkSecond = dateTimeProvider.getDate().getSecond();
        return checkSecond % 2 == 0;
    }
}
