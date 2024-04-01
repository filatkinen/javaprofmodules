package ru.otus.filatkinen.task03;

public enum Status {
    Open(1), Work(2), Closed(3);

    public final int value;

    Status(int value){
        this.value=value;
    }
}
