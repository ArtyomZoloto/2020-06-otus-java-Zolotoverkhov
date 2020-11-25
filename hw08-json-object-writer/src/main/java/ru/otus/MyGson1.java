package ru.otus;

import javax.json.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Object-to-JSON converter
 */
public class MyGson1 implements MyGson {

    /**
     * Convert object to json.
     * Object fields can have primitive types, Strings, Collections and Arrays.
     * Collections can be nested also.
     * @param object if Object is NULL, json string = "null".
     * @return json string with object data.
     * @throws JsonParseException if fields of wrong type.
     */
    public String toJson(Object object) {
        if (object == null) {
            return "null";
        }
        try {
            return toJsonBuilder(object).build().toString();
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            throw new JsonParseException("Can't parse json for object: " + object.toString(), ex);
        }
    }

    private JsonObjectBuilder toJsonBuilder(Object object) throws IllegalAccessException {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                Object invocationResult = field.get(object);
                add(invocationResult, field.getName(), jsonObjectBuilder);
            } catch (NullPointerException ex) {
                continue;
            }
            field.setAccessible(wasAccessible);
        }
        return jsonObjectBuilder;
    }

    private void add(Object object, String name, JsonObjectBuilder jsonObjectBuilder) {
        if (object instanceof Boolean) {
            jsonObjectBuilder.add(name, (Boolean) object);
        }
        if (object instanceof Byte) {
            jsonObjectBuilder.add(name, (Byte) object);
        }
        if (object instanceof Short) {
            jsonObjectBuilder.add(name, (Short) object);
        }
        if (object instanceof Integer) {
            jsonObjectBuilder.add(name, (Integer) object);
        }
        if (object instanceof Float) {
            jsonObjectBuilder.add(name, (Float) object);
        }
        if (object instanceof Double) {
            jsonObjectBuilder.add(name, (Double) object);
        }
        if (object instanceof Long) {
            jsonObjectBuilder.add(name, (Long) object);
        }
        if (object instanceof String) {
            jsonObjectBuilder.add(name, (String) object);
        }
        if (object.getClass().isArray()) {
            jsonObjectBuilder.add(name, processArray(object));
        }
        if (object instanceof Iterable) {
            jsonObjectBuilder.add(name, processCollection((Iterable) object));
        }
    }

    private JsonArrayBuilder processCollection(Iterable iterable) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof Boolean) {
                builder.add((boolean) object);
            } else if (object instanceof Byte) {
                builder.add((byte) object);
            } else if (object instanceof Short) {
                builder.add((short) object);
            } else if (object instanceof Integer) {
                builder.add((int) object);
            } else if (object instanceof Float) {
                builder.add((float) object);
            } else if (object instanceof Double) {
                builder.add((double) object);
            } else if (object instanceof Long) {
                builder.add((long) object);
            } else if (object instanceof String) {
                builder.add((String) object);
            } else {
                builder.add(processCollection((Iterable) object));
            }
        }
        return builder;
    }

    private JsonArrayBuilder processArray(Object object) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        String type = object.getClass().getTypeName();
        switch (type) {
            case "boolean[]":
                for (boolean b : (boolean[]) object) {
                    arrayBuilder.add(b);
                }
            case "byte[]":
                for (byte b : (byte[]) object) {
                    arrayBuilder.add(b);
                }
                break;
            case "short[]":
                for (short s : (short[]) object) {
                    arrayBuilder.add(s);
                }
                break;
            case "int[]":
                for (int i : (int[]) object) {
                    arrayBuilder.add(i);
                }
                break;
            case "float[]":
                for (float f : (float[]) object) {
                    arrayBuilder.add(f);
                }
                break;
            case "double[]":
                for (double d : (double[]) object) {
                    arrayBuilder.add(d);
                }
                break;
            case "long[]":
                for (long l : (long[]) object) {
                    arrayBuilder.add(l);
                }
                break;
            case "String[]":
                for (String s : (String[]) object) {
                    arrayBuilder.add(s);
                }
                break;
            default:
                throw new IllegalArgumentException("cant parse this type of object!" + object.getClass().getName());
        }
        return arrayBuilder;
    }
}
