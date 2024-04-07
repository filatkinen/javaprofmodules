package ru.otus.filatkinen.executionservice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ExecutorServiceCustom {
    private static final int default_capacity = 10;
    private final int capacity;

    private final LinkedList<Runnable> runnables;
    private final List<Thread> threads;

    private final AtomicBoolean goWork;
    private final AtomicBoolean isShutDown;
    private final AtomicBoolean isFinished;
    private final ReentrantLock lockQueueTasks = new ReentrantLock();


    public ExecutorServiceCustom() {
        this(default_capacity);
    }

    public ExecutorServiceCustom(int initialCapacity) {
        this.capacity = initialCapacity;
        runnables = new LinkedList<>();
        threads = new ArrayList<>();
        goWork = new AtomicBoolean(true);
        isShutDown = new AtomicBoolean(false);
        isFinished = new AtomicBoolean(false);
        createPool();

    }

    private void createPool() {
        for (int i = 0; i < capacity; i++) {
            Thread t = new Thread(() -> {
                while (goWork.get()) {
                    Optional<Runnable> optionalRunnable = getNextRunnable();
                    optionalRunnable.ifPresent(Runnable::run);
                }
            });
            threads.add(t);
        }
        for (int i = 0; i < capacity; i++) {
            threads.get(i).start();
        }
    }

    public boolean getIsFinished() {
        return isFinished.get();
    }

    private Optional<Runnable> getNextRunnable() {
        Optional<Runnable> optionalRunnable = Optional.empty();
        try {
            lockQueueTasks.lock();
            if (isShutDown.get() && runnables.isEmpty()) {
                goWork.set(false);
                isFinished.set(true);
            } else if (!runnables.isEmpty()) {
                optionalRunnable = Optional.of(runnables.pop());
            }
        } finally {
            lockQueueTasks.unlock();
        }
        return optionalRunnable;
    }

    public void submitTask(Runnable r) {
        try {
            lockQueueTasks.lock();
            if (isShutDown.get()) {
                throw new IllegalStateException();
            }
            runnables.add(r);
        } finally {
            lockQueueTasks.unlock();
        }
    }

    public void shutdown() {
        isShutDown.set(true);
    }
}
