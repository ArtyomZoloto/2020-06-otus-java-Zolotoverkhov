package ru.otus.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvocationHandlerImpl implements InvocationHandler {
    private final TestLogging originalTestLogging;
    private Map<String, Class[]> methodsToLog = new HashMap<>();

    public InvocationHandlerImpl(TestLogging testLogging) {
        this.originalTestLogging = testLogging;
        fillMethodsToLog(testLogging);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (match(method, args)) {
            log();
        }
        method.invoke(originalTestLogging, args);
        return null;
    }

    private void log() {
        System.out.println("executed!");
    }

    private void fillMethodsToLog(TestLogging testLogging) {
        for (Method method : testLogging.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                methodsToLog.put(method.getName(), method.getParameterTypes());
            }
        }
    }

    private boolean match(Method method, Object[] args) {
        Class[] params = methodsToLog.get(method.getName());
        if (params == null || params.length != args.length) {
            return false;
        }
        for (int i = 0; i < args.length; i++) {
           Class argClass =  args[i].getClass();
           Class methodRequiredArgClass = method.getParameterTypes()[i];
           if (!argClass.getName().equals(methodRequiredArgClass.getName())) {
               //map()
           }
        }
        return true;
    }
}
