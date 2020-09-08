package ru.otus.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс для перехвата методов и вызова логирования.
 * Действует на все методы, которые называются так же, как
 * и исходные метод, помеченный аннотацией @Log.
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private final TestLogging originalTestLogging;
    private Set<String> methodsToLog = new HashSet<>();
    StringBuilder sb = new StringBuilder();

    public InvocationHandlerImpl(TestLogging testLogging) {
        this.originalTestLogging = testLogging;
        fillMethodsToLog(testLogging);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodsToLog.contains(getMethodSignatureString(method))) {
            log(method, args);
        }
        method.invoke(originalTestLogging, args);
        return null;
    }

    private void log(Method method, Object[] args) {
        sb.delete(0, sb.length());
        sb.append("executed method: ");
        sb.append(method.getName());
        sb.append(", params:");
        sb.append(Arrays.toString(args));
        System.out.println(sb.toString());
    }

    private void fillMethodsToLog(TestLogging testLogging) {
        for (Method method : testLogging.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                methodsToLog.add(getMethodSignatureString(method));
            }
        }
    }

    private String getMethodSignatureString(Method method) {
        sb.delete(0, sb.length());
        sb.append(method.getName());
        for (Class cl : method.getParameterTypes()) {
            sb.append(cl.getName());
        }
        return sb.toString();
    }
}
