package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;


public class TestEngine {

    private List<Method> testMethods = new ArrayList();
    private List<Method> beforeMethods = new ArrayList();
    private List<Method> afterMethods = new ArrayList();
    private int passed;
    private int failed;
    private final Class testClass;

    public TestEngine(Class testClass) {
        this.testClass = testClass;
    }

    static void start(String className) {
        try {
            Class clazz = Class.forName(className);
            TestEngine testEngine = new TestEngine(clazz);
            testEngine.addMethods();
            testEngine.runTests();
            testEngine.printResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMethods() {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
    }

    private void runTests() throws Exception {
        Constructor<?> constructor = testClass.getConstructor();
        Object testObject = constructor.newInstance();
        for (Method testMethod : testMethods) {
            runBeforeAndAfter(beforeMethods, testObject);
            runTest(testMethod, testObject);
            runBeforeAndAfter(afterMethods, testObject);
        }
    }

    private void runBeforeAndAfter(List<Method> methods, Object testObject) {
        for (Method method : methods) {
            try {
                method.invoke(testObject);
            } catch (Exception e) {
                System.out.println("Exception during maintance (@Before or @After) methods");
                System.out.println(e.getCause());
            }
        }
    }

    private void runTest(Method method, Object testObject) {
        try {
            System.out.println("Testing " + method.getName() + "...");
            method.invoke(testObject);
            passed++;
        } catch (Exception e) {
            failed++;
            System.out.println("Exception during test method: " + method.getName());
            System.out.println(e.getCause());
        }
    }

    private void printResults() {
        System.out.println("Done! " + (passed + failed) + " tests complete.");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}
