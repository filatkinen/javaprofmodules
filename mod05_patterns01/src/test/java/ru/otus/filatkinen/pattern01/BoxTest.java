package ru.otus.filatkinen.pattern01;

import org.junit.jupiter.api.Test;
import ru.otus.filatkinen.pattern01.Box.Box;
import ru.otus.filatkinen.pattern01.Box.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    List<String> list1,list2, list3,list4;

    void Test(){
        List<String> uniontList = new ArrayList<>();
        if (list1!=null){
            uniontList.addAll(list1);
        }
        if (list2!=null){
            uniontList.addAll(list2);
        }
        if (list3!=null){
            uniontList.addAll(list3);
        }
        if (list4!=null){
            uniontList.addAll(list4);
        }
        List<String> resultList = new ArrayList<>();
        Box box = new Box(list1, list2, list3, list4);

        Iterator iterator = box.getIterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next().toString());
        }

        assertEquals(uniontList, resultList);
    }

    @Test
    void shouldRetrieveNotNullStringWhenAllListsNotEmpty() {
        list1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        list2 = new ArrayList<>(Arrays.asList("4", "5", "6"));
        list3 = new ArrayList<>(Arrays.asList("7", "8", "9"));
        list4 = new ArrayList<>(Arrays.asList("10", "11", "12"));

        Test();
    }

    @Test
    void runBoxTestBorderSomeListIsNullAndSomeListIsEmpty() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = null;
        list4 = null;

        Test();
    }

    @Test
    public void runBoxTestReturnNoSuchElementException() {
        list1 = null;
        list2 = null;
        list3 = null;
        list4 = null;

        Box box = new Box(list1, list2, list3, list4);
        Iterator iterator = box.getIterator();

        Exception exception = assertThrows(NoSuchElementException.class, iterator::next);

        assertEquals(NoSuchElementException.class, exception.getClass());
    }

}
