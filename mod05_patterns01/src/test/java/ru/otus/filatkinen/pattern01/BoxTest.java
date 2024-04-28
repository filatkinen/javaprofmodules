package ru.otus.filatkinen.pattern01;

import org.junit.jupiter.api.Test;
import ru.otus.filatkinen.pattern01.Box.Box;
import ru.otus.filatkinen.pattern01.Box.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxTest {

    @Test
    void runBoxTest() {
        List<String> list1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        List<String> list2 = new ArrayList<>(Arrays.asList("4", "5", "6"));
        List<String> list3 = new ArrayList<>(Arrays.asList("7", "8", "9"));
        List<String> list4 = new ArrayList<>(Arrays.asList("10", "11", "12"));

        List<String> uniontList= new ArrayList<>();
        uniontList.addAll(list1);
        uniontList.addAll(list2);
        uniontList.addAll(list3);
        uniontList.addAll(list4);

        List<String> resultList= new ArrayList<>();
        Box box = new Box(list1,list2,list3,list4);

        Iterator iterator = box.getIterator();
        while (iterator.hasNext()){
            resultList.add(iterator.next().toString());
        }


        assertEquals(resultList, uniontList);
    }
}
