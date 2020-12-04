package ru.otus.processor.homework;

public class ProcessorGenExEvenSecException extends RuntimeException {
    public ProcessorGenExEvenSecException(int currentSecond) {
        super("An exception that occurs at an even second: " + currentSecond);
    }
}
