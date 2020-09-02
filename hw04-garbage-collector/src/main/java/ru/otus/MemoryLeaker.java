package ru.otus;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryLeaker {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        List<Date> list = new LinkedList<>();
        while (true) {
            int i = 0;
            while (i++ < 1000) {
                list.add(new Date());
                list.add(new Date());
            }
            while (i++ < 1900) {
                list.remove(random.nextInt(10));
            }
            Thread.sleep(10);
        }
    }
}
