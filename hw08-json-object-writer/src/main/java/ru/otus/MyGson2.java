package ru.otus;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class MyGson2 implements MyGson{

    private static final Set<Class> simpleTypes = Collections.unmodifiableSet(Set.of(
            Boolean.class,
            Byte.class,
            Character.class,
            Short.class,
            Integer.class,
            Float.class,
            Double.class,
            Long.class,
            String.class));

    @Override
    public String toJson(Object object){
        if (object == null) {
            return "null";
        }
        if (simpleTypes.contains(object.getClass())){
            return object.toString();
        }
        if (object.getClass().isArray()){
            return new JSONArray(object).toString();
        }
        if (object instanceof Collection) {
            return new JSONArray((Collection)object).toString();
        }
        return new JSONObject(object).toString();
    }
}