package ru.otus;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyGson {
    /**
     * Create json from object
     * @param object input object
     */
    public String toJson(Object object) throws IllegalAccessException, InvocationTargetException {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);
            Object invocationResult = field.get(object);
            if (invocationResult instanceof Boolean) {
                jsonObjectBuilder.add(field.getName(), (Boolean) invocationResult);
            }
            if (invocationResult instanceof Byte) {
                jsonObjectBuilder.add(field.getName(), (Byte) invocationResult);
            }
            if (invocationResult instanceof Short) {
                jsonObjectBuilder.add(field.getName(), (Short) invocationResult);
            }
            if (invocationResult instanceof Integer) {
                jsonObjectBuilder.add(field.getName(), (Integer) invocationResult);
            }
            if (invocationResult instanceof Float) {
                jsonObjectBuilder.add(field.getName(), (Float) invocationResult);
            }
            if (invocationResult instanceof Double) {
                jsonObjectBuilder.add(field.getName(), (Double) invocationResult);
            }
            if (invocationResult instanceof Long) {
                jsonObjectBuilder.add(field.getName(), (Long) invocationResult);
            }
            if (invocationResult instanceof String) {
                jsonObjectBuilder.add(field.getName(), (String) invocationResult);
            }
            if (invocationResult.getClass().isArray()) {
                System.out.println("Array!");
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
               //TODO: continue here
                jsonObjectBuilder.add(field.getName(),null);
            }
            field.setAccessible(wasAccessible);
        }
        return jsonObjectBuilder.build().toString();
    }

    public static void main(String[] args) {
        Object o = new AnyObject(true, 22,"Artem",10,new int[]{1,2,3}, List.of("a","b","c"));
        MyGson myGson = new MyGson();
        try {
            System.out.println(myGson.toJson(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
