package ru.otus.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ObjectFactory {
    public static TestLogging getTestLogin() {
        TestLogging testLogging = new TestLoggingImpl();
        InvocationHandler invocationHandler = new InvocationHandlerImpl(testLogging);
        return (TestLogging) Proxy.newProxyInstance(TestLogging.class.getClassLoader(),
                new Class[]{TestLogging.class, Log.class}, invocationHandler
        );
    }
}
