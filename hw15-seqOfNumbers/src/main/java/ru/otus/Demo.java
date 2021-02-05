package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        CounterSynchronized numbersThread = new CounterSynchronized();
        logger.info("Trying to start Threads");
        new Thread(() -> numbersThread.action(Thread.currentThread().getName())).start();
        new Thread(() -> numbersThread.action(Thread.currentThread().getName())).start();
    }
}
