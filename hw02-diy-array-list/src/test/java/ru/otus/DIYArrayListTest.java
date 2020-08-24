package ru.otus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals(3, list.size());
    }

    @Test
    void testRemove() {
        DIYArrayList<String> list = new DIYArrayList<>();
        list.add("a"); //1
        list.add("b"); //2
        list.add("c"); //3
        list.add("d"); //4
        list.add("e"); //5
        assertEquals(5, list.size());

        String removed = list.remove(1); //remove "b"
        assertEquals("b", removed);
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("d", list.get(2));
        assertEquals("e", list.get(3));

        removed = list.remove(2); //remove "d"
        assertEquals("d", removed);
        assertEquals("a", list.get(0));
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

        DIYArrayList<String> namesList = new DIYArrayList<>();
        namesList.add("3Pavel");
        namesList.add("1Ivan");
        namesList.add("2Marina");

        Collections.sort(namesList, String::compareTo);

        assertEquals("1Ivan", namesList.get(0));
        assertEquals("2Marina", namesList.get(1));
        assertEquals("3Pavel", namesList.get(2));
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
        source.add("Cat");
        source.add("Dog");
        source.add("Snake");

        source.add(0,"Mouse");
        source.add(2,"Horse");

        assertEquals("Mouse",source.get(0));
        assertEquals("Cat",source.get(1));
        assertEquals("Horse",source.get(2));
        assertEquals("Dog",source.get(3));
        assertEquals("Snake",source.get(4));
    }

    @Test
    void testClear(){
        DIYArrayList<String> source = new DIYArrayList<>();
        source.add("Cat");
        source.add("Dog");
        source.add("Snake");

        source.clear();

        assertEquals(0,source.size());
    }

}