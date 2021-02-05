package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterSynchronized {
    private static final Logger logger = LoggerFactory.getLogger(CounterSynchronized.class);
    private String last;
    private int counterValue;
    private boolean counterReverse;

    {
        last = "";
        counterReverse = false;
        counterValue = 1;
    }

    public synchronized void action(String message) {
        logger.info("Thread started");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                while (last.equals(message)) {
                    this.wait();
                }
                last = message;
                sleep();
                notifyAll();
                logger.info("Current counter value: " + counterValue);
                changeCounterValue();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new CounterSynchronized.NotInterestingException(ex);
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static class NotInterestingException extends RuntimeException {
        NotInterestingException(InterruptedException ex) {
            super(ex);
        }
    }

    private void changeCounterValue() {
        checkCounterValue();
        if (!counterReverse) counterValue++;
        else counterValue--;
    }

    private void checkCounterValue() {
        if (counterValue == 10) counterReverse = true;
        else if (counterValue == 1) counterReverse = false;
    }
}
