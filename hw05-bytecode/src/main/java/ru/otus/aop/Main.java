package ru.otus.aop;

public class Main {
    public static void main(String[] args) {
        TestLogging testLogging = ObjectFactory.getTestLogin();
        testLogging.calculation(10);
    }
}
