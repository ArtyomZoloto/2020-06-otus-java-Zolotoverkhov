package ru.otus.aop;

public class Main {
    public static void main(String[] args) {
        TestLogging testLogging = ObjectFactory.getTestLogin();
        testLogging.calculation(10);
        testLogging.calculation(10,20);
        testLogging.calculation(10,20,"Moscow is the capital of Russia");
    }
}
