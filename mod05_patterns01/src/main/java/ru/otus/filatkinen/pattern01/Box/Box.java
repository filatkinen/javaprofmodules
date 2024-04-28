package ru.otus.filatkinen.pattern01.Box;

import java.util.ArrayList;
import java.util.List;

public class Box implements Collection {
    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    private List<String> list4;


    public Box(List<String> list1, List<String> list2, List<String> list3, List<String> list4) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
    }


    @Override
    public Iterator getIterator() {
        return new BoxIterator();
    }

    private class BoxIterator implements Iterator {
        private List<String> list;
        private int idx;

        public BoxIterator() {
            list = new ArrayList<>();
            list.addAll(list1);
            list.addAll(list2);
            list.addAll(list3);
            list.addAll(list4);
            idx = 0;
        }


        @Override
        public boolean hasNext() {
            return idx < list.size();
        }

        @Override
        public Object next() {
            return list.get(idx++);
        }
    }
}
