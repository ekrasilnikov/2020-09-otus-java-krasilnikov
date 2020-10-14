package ru.otus;

import static com.google.common.base.Objects.equal;

public class HelloOtus {
    public static void main(String... args) {
        boolean result = equal(null, null);
        System.out.println("Result of the method equal for null string - " + result);
    }
}
