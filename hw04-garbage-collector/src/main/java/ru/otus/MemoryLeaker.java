package ru.otus;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryLeaker {
    public static void main(String[] args) throws Exception {
        long was = System.currentTimeMillis();
        Random random = new Random();
        List<Date> list = new LinkedList<>();
        int j = 0;
        while (j++ < 50_000) {
            int i = 0;
            while (i++ < 1000) {
                list.add(new Date());
                list.add(new Date());
            }
            while (i++ < 1999) {
                list.remove(random.nextInt(10));
            }
        }
        System.out.println("done!");
        System.out.println(System.currentTimeMillis() - was);
    }
}
