package ru.otus.filatkinen.executionservice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class ExecutorServiceCustom {
    private static final int default_capacity = 10;
    private final int capacity;

    private final LinkedList<Runnable> runnables;
    private final List<Thread> threads;

    private final AtomicBoolean isShutDown;
    private final AtomicInteger activeThreads;
    private final ReentrantLock lockQueueTasks = new ReentrantLock();
    private final Object monitorJobsLine;
    private final Object monitorActiveThreadsCount;

    public ExecutorServiceCustom() {
        this(default_capacity);
    }

    public ExecutorServiceCustom(int initialCapacity) {
        this.capacity = initialCapacity;
        runnables = new LinkedList<>();
        threads = new ArrayList<>();
        isShutDown = new AtomicBoolean(false);
        activeThreads = new AtomicInteger(this.capacity);
        monitorJobsLine = new Object();
        monitorActiveThreadsCount = new Object();
        createPool();

    }

    private void createPool() {
        for (int i = 0; i < capacity; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    Optional<Runnable> optionalRunnable;
                    synchronized (monitorJobsLine) {
                        optionalRunnable = getNextRunnable();
                        if (optionalRunnable.isEmpty() && isShutDown.get()) {
                            break;
                        }
                        if (optionalRunnable.isEmpty()) {
                            try {
                                monitorJobsLine.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        monitorJobsLine.notifyAll();
                    }
                    optionalRunnable.ifPresent(Runnable::run);
                }
                synchronized (monitorActiveThreadsCount) {
                    activeThreads.decrementAndGet();
                    monitorActiveThreadsCount.notifyAll();
                }
            });
            threads.add(t);
        }
        for (int i = 0; i < capacity; i++) {
            threads.get(i).start();
        }
    }

    public void waiting() {
        while (activeThreads.get() != 0) {
            synchronized (monitorActiveThreadsCount) {
                try {
                    monitorActiveThreadsCount.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Optional<Runnable> getNextRunnable() {
        Optional<Runnable> optionalRunnable = Optional.empty();
        try {
            lockQueueTasks.lock();
            if (!runnables.isEmpty()) {
                optionalRunnable = Optional.of(runnables.pop());
            }
        } finally {
            lockQueueTasks.unlock();
        }
        return optionalRunnable;
    }

    public void submitTask(Runnable r) {
        synchronized (monitorJobsLine) {
            if (isShutDown.get()) {
                throw new IllegalStateException();
            }
            try {
                lockQueueTasks.lock();
                runnables.add(r);
            } finally {
                lockQueueTasks.unlock();
            }
            monitorJobsLine.notifyAll();
        }
    }

    public void shutdown() {
        isShutDown.set(true);
    }
}
