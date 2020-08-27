package ru.otus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DIYArrayListTest {

    static String name1 = "Ivan";
    static String name2 = "Danil";
    static String name3 = "Petr";
    static String name4 = "Olga";
    static String name5 = "Matvey";
    static String name6 = "Ignat";

    @Test
    void testAdd() {
        DIYArrayList<String> list = new DIYArrayList<>();
        int i = 0;
        while (i < 50) {
            list.add("name " + i);
            i++;
        }
        assertEquals(50, list.size());
        assertEquals("name 0", list.get(0));
        assertEquals("name 2", list.get(2));
        assertEquals("name 40", list.get(40));
        assertEquals("name 49", list.get(49));
    }

    @Test
    void testRemove() {
        DIYArrayList<String> list = new DIYArrayList<>();
        list.add("a"); //0
        list.add("b"); //1
        list.add("c"); //2
        list.add("d"); //3
        list.add("e"); //4
        assertEquals(5, list.size());

        String removed = list.remove(0); //remove "a"
        assertEquals("a", removed);
        assertEquals("b", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("d", list.get(2));
        assertEquals("e", list.get(3));

        removed = list.remove(2); //remove "d"
        assertEquals("d", removed);
        assertEquals("b", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("e", list.get(2));

    }

    @Test
    void testAddAll() {
        DIYArrayList<String> myList = new DIYArrayList<>();
        Collections.addAll(myList, name1, name2, name3, name4, name5, name6);
        assertEquals(6, myList.size());
    }

    @Test
    void testCopy() {
        DIYArrayList<String> source = new DIYArrayList<>();
        Collections.addAll(source, name1, name2, name3, name4, name5, name6);
        ArrayList<String> dest = new ArrayList<>(source.size());
        Collections.addAll(dest, null, null, null, null, null, null);
        Collections.copy(dest, source);

        assertEquals(6, dest.size());
        assertEquals(name1, dest.get(0));
        assertEquals(name5, dest.get(4));
    }

    @Test
    void testSort() {

        DIYArrayList<Integer> namesList = new DIYArrayList<>();
        namesList.add(Integer.valueOf(3));
        namesList.add(Integer.valueOf(1));
        namesList.add(Integer.valueOf(2));

        Comparator<Integer> comparator = (o1, o2) -> {
            if (o2 == null || o1 == null) {
                return 0;
            }
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        };

        Collections.sort(namesList, comparator);

        assertEquals(Integer.valueOf(1), namesList.get(0));
        assertEquals(Integer.valueOf(2), namesList.get(1));
        assertEquals(Integer.valueOf(3), namesList.get(2));
    }

    @Test
    void testSubList() {
        DIYArrayList<String> source = new DIYArrayList<>();
        Collections.addAll(source, name1, name2, name3, name4, name5, name6);
        List<String> sublist = source.subList(1, 3);
        assertEquals(3, sublist.size());
        assertEquals(name2, sublist.get(0));
        assertEquals(name3, sublist.get(1));
        assertEquals(name4, sublist.get(2));
    }

    @Test
    void testContains() {
        DIYArrayList<String> source = new DIYArrayList<>();
        source.add("Otus");
        assertTrue(source.contains("Otus"));
        assertFalse(source.contains("Circus"));
    }

    @Test
    void testAddAtIndex() {
        DIYArrayList<String> source = new DIYArrayList<>();
        source.add("a");
        source.add("b");
        source.add("c");
        source.add("d");
        source.add("e");
        source.add("f");
        source.add("g");
        source.add("h");

        source.add(1, "a1");
        source.add(7, "g1");

        assertEquals("a", source.get(0));
        assertEquals("a1", source.get(1));
        assertEquals("b", source.get(2));
        assertEquals("c", source.get(3));
        assertEquals("g1", source.get(7));
        assertEquals("g", source.get(8));

    }

    @Test
    void testClear() {
        DIYArrayList<String> source = new DIYArrayList<>();
        source.add("Cat");
        source.add("Dog");
        source.add("Snake");

        source.clear();

        assertEquals(0, source.size());
    }

}