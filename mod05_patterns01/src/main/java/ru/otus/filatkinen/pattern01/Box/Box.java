package ru.otus.filatkinen.pattern01.Box;

import java.util.List;

public class Box {
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


    public Iterator getIterator() {
        return new BoxIterator();
    }

    private class BoxIterator implements Iterator {
        private List<String>[] list;
        private int list_idx;
        private int idx;

        public BoxIterator() {
            list = new List[4];
            list[0] = list1;
            list[1] = list2;
            list[2] = list3;
            list[3] = list4;
            list_idx=0;
            idx = 0;
        }


        @Override
        public boolean hasNext() {
            while (list_idx<list.length && list[list_idx]!=null) {
                if (idx<list[list_idx].size()){
                    return true;
                }
                list_idx++;
                idx=0;
            }
            return false;
        }

        @Override
        public Object next() {
            return list[list_idx].get(idx++);
        }
    }
}
