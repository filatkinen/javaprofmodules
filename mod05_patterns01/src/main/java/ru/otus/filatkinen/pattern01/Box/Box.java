package ru.otus.filatkinen.pattern01.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Box {
    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    private List<String> list4;


    public Box(List<String> list1, List<String> list2, List<String> list3, List<String> list4) {
        if (list1 != null) {
            this.list1 = new ArrayList<>(list1);
            ;
        }
        if (list2 != null) {
            this.list2 = new ArrayList<>(list2);
            ;
        }
        if (list3 != null) {
            this.list3 = new ArrayList<>(list3);
            ;
        }
        if (list4 != null) {
            this.list4 = new ArrayList<>(list4);
            ;
        }
    }


    public Iterator getIterator() {
        return new BoxIterator(list1, list2, list3, list4);
    }

    private static class BoxIterator implements Iterator {
        private List<String>[] list;
        private int list_idx;
        private int idx;

        public BoxIterator(List<String> list1, List<String> list2, List<String> list3, List<String> list4) {
            list = new List[4];
            list[0] = list1;
            list[1] = list2;
            list[2] = list3;
            list[3] = list4;
            list_idx = 0;
            idx = 0;
        }


        @Override
        public boolean hasNext() {
            while (list_idx < list.length && list[list_idx] != null) {
                if (idx < list[list_idx].size()) {
                    return true;
                }
                list_idx++;
                idx = 0;
            }
            return false;
        }

        @Override
        public Object next() {
            if (list_idx >= list.length || list[list_idx] == null || idx >= list[list_idx].size()) {
                throw new NoSuchElementException();
            }
            return list[list_idx].get(idx++);
        }
    }
}
