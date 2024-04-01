package ru.otus.filatkinen.task03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamApiTest {

    List<Task> tasks;

    @BeforeEach
    void init() {
        tasks = new ArrayList<>();
        tasks.add(new Task().setID(1).setName("Task1").setStatus(Status.Open));
        tasks.add(new Task().setID(2).setName("Task2").setStatus(Status.Work));
        tasks.add(new Task().setID(3).setName("Task3").setStatus(Status.Closed));
        tasks.add(new Task().setID(4).setStatus(Status.Open));
        tasks.add(new Task().setID(5).setName("Task5").setStatus(Status.Work));
        tasks.add(new Task().setID(6).setName("Task6"));
        tasks.add(new Task().setID(7).setName("Task7").setStatus(Status.Open));
        tasks.add(new Task().setID(8).setName("Task8").setStatus(Status.Work));
    }

    @Test
    public void testTaskListByStatus() {
        List<Task> taskOpen = tasks.stream().filter(x -> x.getState() != null).
                filter(x -> x.getState().equals(Status.Open)).toList();
        assertEquals(3, taskOpen.size());

        List<Task> taskWork = tasks.stream().filter(x -> x.getState() != null).
                filter(x -> x.getState().equals(Status.Work)).toList();
        assertEquals(3, taskWork.size());

        List<Task> taskClosed = tasks.stream().filter(x -> x.getState() != null).
                filter(x -> x.getState().equals(Status.Closed)).toList();
        assertEquals(1, taskClosed.size());
    }


    @Test
    public void testGetTaskById() {
        int checkID = 8;
        long countCheckID = tasks.stream().filter(x -> x.getId() == checkID).count();
        assertEquals(1, countCheckID);

        int wrongID = 25;
        long countWrongID = tasks.stream().filter(x -> x.getId() == wrongID).count();
        assertEquals(0, countWrongID);
    }

    @Test
    public void testTaskListSortedByStatus() {
        Comparator<Task> cmpTaskStatus = ((x, y) -> {
            if (x.getState() == null) {
                return -1;
            }
            if (y.getState() == null) {
                return 1;
            }
            if (x.getState().equals(y.getState())) {
                return 0;
            }
            return x.getState().value < y.getState().value ? -1 : 1;
        });

        List<Task> listFromList= new ArrayList<>(tasks);
        listFromList.sort(cmpTaskStatus);

        List<Task> listFromStream= tasks.stream().sorted(cmpTaskStatus).toList();

        assertEquals(listFromList, listFromStream);
    }

    @Test
    public void testCountByStatus() {
        long taskOpen = tasks.stream().filter(x -> x.getState() != null)
                .filter(x -> x.getState().equals(Status.Open)).count();
        assertEquals(3, taskOpen);

        long taskWork = tasks.stream().filter(x -> x.getState() != null).
                filter(x -> x.getState().equals(Status.Work)).count();
        assertEquals(3, taskWork);

        long taskClosed = tasks.stream().filter(x -> x.getState() != null)
                .filter(x -> x.getState().equals(Status.Closed)).count();
        assertEquals(1, taskClosed);
    }
}
