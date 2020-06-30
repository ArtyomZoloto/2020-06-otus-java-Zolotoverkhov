package ru.otus;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Класс для первого домашнего задания. Использует некоторые полезные методы пакета Google Guava.
 */
public class HelloOtus {

    public static void main(String[] args) {
        List<String> words = Lists.newArrayList("Hello", "Otus,", "it", "is ");
        String greetingString = Joiner.on(" ").join(words) + new Student("Артем", "Золотоверхов");
        System.out.println(greetingString);
    }

    static class Student {
        private final String name;
        private final String surName;

        public Student(String name, String surName) {
            this.name = name;
            this.surName = surName;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("имя", name)
                    .add("фамилия", surName)
                    .toString();
        }
    }
}