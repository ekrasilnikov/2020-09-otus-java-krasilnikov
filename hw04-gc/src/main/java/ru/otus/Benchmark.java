package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            List<Object> list = new ArrayList<>();
            Object[] array = new Object[local];
            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
            }
            Thread.sleep(10); //Label_1
            list.add(Arrays.asList(array));
            size *= 1.1f;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }
}
