package ru.otus;

/*
* Simple test launcher.
* To run test enter in the console:
* $ ./gradlew runTest
* */
public class TestRunner {
    public static void main(String[] args) {
        String className = "ru.otus.test.TestClass";
        TestEngine.start(className);
    }
}
