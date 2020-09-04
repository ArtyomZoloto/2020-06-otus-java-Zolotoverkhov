package ru.otus.aop;

public class TestLoggingImpl implements TestLogging {
    @Log
    public void calculation(int param) {};
}