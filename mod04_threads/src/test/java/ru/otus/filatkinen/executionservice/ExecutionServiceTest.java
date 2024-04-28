package ru.otus.filatkinen.executionservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionServiceTest {

    @Test
    void runSimpleTwoThreadsTest() {
        int threads = 2;
        int tasks = 10;
        int taskDelay = 100;
        long start = System.nanoTime();

        ExecutorServiceCustom executorServiceCustom = new ExecutorServiceCustom(threads);
        for (int i = 0; i < tasks; i++) {
            executorServiceCustom.submitTask(new TstClass(taskDelay));
        }

        executorServiceCustom.shutdown();

        executorServiceCustom.waiting();

        long millSeconds = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Threads=%d, tasks=%d, taskDelay=%d, time to take=%d%n", threads, tasks, taskDelay, millSeconds);
        assertTrue(millSeconds >= 500 && millSeconds < 600);
    }

    @Test
    void runSimpleOneThreadTest() {
        int threads = 1;
        int tasks = 3;
        int taskDelay = 100;
        long start = System.nanoTime();

        ExecutorServiceCustom executorServiceCustom = new ExecutorServiceCustom(threads);
        for (int i = 0; i < tasks; i++) {
            executorServiceCustom.submitTask(new TstClass(taskDelay));
        }
        executorServiceCustom.shutdown();

        executorServiceCustom.waiting();

        long millSeconds = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Threads=%d, tasks=%d, taskDelay=%d, time to take=%d%n", threads, tasks, taskDelay, millSeconds);
        assertTrue(millSeconds >= 300 && millSeconds < 350);
    }

    @Test
    void runExceptionAfterShutdownTest() {
        int threads = 1;
        int tasks = 3;
        int taskDelay = 100;

        ExecutorServiceCustom executorServiceCustom = new ExecutorServiceCustom(threads);
        for (int i = 0; i < tasks; i++) {
            executorServiceCustom.submitTask(new TstClass(taskDelay));
        }
        executorServiceCustom.shutdown();

        try {
            Thread.sleep(taskDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            executorServiceCustom.submitTask(new TstClass(taskDelay));
        });
        System.out.println("got Exception: " + exception.getClass());
        assertEquals(exception.getClass(), IllegalStateException.class);
    }
}


class TstClass implements Runnable {
    private final int delay;

    public TstClass(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}