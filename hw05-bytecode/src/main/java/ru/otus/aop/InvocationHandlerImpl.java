package ru.otus.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
    private final TestLogging originalTestLogging;

    public InvocationHandlerImpl(TestLogging testLogging) {
        this.originalTestLogging = testLogging;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Log.class)) {
            log();
        }
        method.invoke(originalTestLogging, args);
        return null;
    }

    private void log() {
        System.out.println("executed!");
    }
}
