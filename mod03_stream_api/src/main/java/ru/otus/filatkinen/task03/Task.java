package ru.otus.filatkinen.task03;

public class Task {
    private int id;
    private String name;
    private Status state;

    public Task() {
    }

    public Task setID(int id) {
        this.id = id;
        return this;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public Task setStatus(Status state) {
        this.state = state;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

}


