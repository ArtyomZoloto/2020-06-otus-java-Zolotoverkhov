package ru.otus.test;


import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class TestClass {

    int num;

    @Before
    public void setUp() {
        num = 5;
        System.out.println("Before 1");
    }

    @Before
    public void setUp2() {
        num = 5;
        System.out.println("Before 2");
    }

    @After
    public void tearDown() {
        System.out.println("After 1");
        num = 0;
    }

    @After
    public void tearDown2() {
        num = 0;
        System.out.println("After 2");
    }

    @Test
    public void testSum() {
        System.out.println("Test 1");
        int calculated = num + 10;
        assertThat(calculated).isEqualTo(15);
        throw new ArithmeticException();
    }

    @Test
    public void testMultiply() {
        System.out.println("Test 2");
        int calculated = num * 10;
        assertThat(calculated).isEqualTo(50);
    }

}
