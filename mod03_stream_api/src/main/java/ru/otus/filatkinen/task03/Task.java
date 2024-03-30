package ru.otus.filatkinen.task03;

public class Task {
    public int id;
    public String name;
    public Status state;

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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

}


