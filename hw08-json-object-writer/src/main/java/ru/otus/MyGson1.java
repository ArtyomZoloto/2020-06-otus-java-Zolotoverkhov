package ru.otus;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Object-to-JSON converter based on  javax.json
 */
public class MyGson1 implements MyGson {

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

    /**
     * Convert object to json.
     * Object fields can have primitive types, Strings, Collections and Arrays or other objects.
     * Collections and Arrays can be nested.
     *
     * @param object if Object is NULL return "null" string value.
     * @return json string with object data.
     * @throws JsonParseException if fields of wrong type.
     */
    public String toJson(Object object) {
        if (object == null) {
            return "null";}
            else if (object instanceof String || object instanceof Character) {
                return "\"" + object.toString() + "\"";
        } else if (isSimpleType(object)) {
            return object.toString();
        } else if (object.getClass().isArray()) {
            return createArrayBuilderForArray(object).build().toString();
        } else if (object instanceof Collection) {
            return createArrayBuilderForCollection(object).build().toString();
        } else {
            return toComplexJsonObject(object).build().toString();
        }
    }

    private JsonObjectBuilder toComplexJsonObject(Object object) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object invocationResult = field.get(object);
                if (invocationResult.getClass().isArray()) {
                    jsonObjectBuilder.add(field.getName(), createArrayBuilderForArray(invocationResult));
                } else if (invocationResult instanceof Collection) {
                    jsonObjectBuilder.add(field.getName(), createArrayBuilderForCollection(invocationResult));
                } else {
                    jsonObjectBuilder.add(field.getName(), createJsonValue(invocationResult));
                }
            } catch (NullPointerException ex) {
                continue;
            } catch (IllegalAccessException ex) {
                throw new JsonParseException("parsing error", ex);
            }
        }
        return jsonObjectBuilder;
    }

    private JsonArrayBuilder createArrayBuilderForArray(Object array) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < Array.getLength(array); i++) {
            processElement(arrayBuilder, Array.get(array, i));
        }
        return arrayBuilder;
    }

    private JsonArrayBuilder createArrayBuilderForCollection(Object collection) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object element : (Collection) collection) {
            processElement(arrayBuilder, element);
        }
        return arrayBuilder;
    }

    private void processElement(JsonArrayBuilder arrayBuilder, Object element) {
        if (isSimpleType(element)) {
            arrayBuilder.add(createJsonValue(element));
        } else if (element.getClass().isArray()) {
            arrayBuilder.add(createArrayBuilderForArray(element));
        } else if (element instanceof Collection) {
            arrayBuilder.add(createArrayBuilderForCollection(element));
        } else {
            arrayBuilder.add(toComplexJsonObject(element));
        }
    }

    private boolean isSimpleType(Object object) {
        return simpleTypes.contains(object.getClass());
    }

    private JsonValue createJsonValue(Object object) {
        if (object instanceof Boolean) {
            return Json.createValue(object.toString());
        } else if (object instanceof Byte) {
            return Json.createValue((byte) object);
        } else if (object instanceof Short) {
            return Json.createValue((short) object);
        }else if (object instanceof Integer) {
            return Json.createValue((int) object);
        } else if (object instanceof Float) {
            return Json.createValue((float) object);
        } else if (object instanceof Double) {
            return Json.createValue((double) object);
        } else if (object instanceof Long) {
            return Json.createValue((long) object);
        } else if (object instanceof String || object instanceof Character) {
            return Json.createValue(object.toString());
        } else {
            throw new JsonParseException("unsupported object type");
        }
    }

}
