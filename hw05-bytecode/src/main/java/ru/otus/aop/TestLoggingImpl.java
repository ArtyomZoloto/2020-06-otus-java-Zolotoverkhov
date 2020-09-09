package ru.otus.aop;

public class TestLoggingImpl implements TestLogging {
    @Log
    public void calculation(int param) {}

    public void calculation(int param, int param2) {}

    @Log
    public void calculation(int param, int param2, String param3) {}
}