package ru.otus;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyGson {
    public String toJson(Object object) {
        if (object != null) {
            Class<?> objClass = object.getClass();
            Field[] objFields = objClass.getDeclaredFields();
            StringBuilder stringBuilder = new StringBuilder("{");
            int currEntry = 0;
            for (Field currField : objFields) {
                String fieldName = currField.getName();
                stringBuilder.append("\"")
                        .append(fieldName)
                        .append("\":")
                        .append(getFieldValue(currField, object));
                if (++currEntry != objFields.length) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        } else return null;
    }

    private String getFieldValue(Field field, Object object) {
        String result = "";
        field.setAccessible(true);
        var fieldType = field.getType();
        try {
            var fieldValue = field.get(object);
            if (fieldType.isPrimitive() || fieldValue instanceof Number) {
                result = getPrimitiveStr(fieldValue);
            } else if (fieldType.isArray()) {
                result = getArrayStr(fieldValue);
            } else if (fieldValue instanceof String) {
                result = getStringStr(fieldValue);
            } else if (fieldValue instanceof Collection) {
                result = getCollectionString(fieldValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getPrimitiveStr(Object obj) {
        return obj.toString();
    }

    private String getArrayStr(Object obj) {
        StringBuilder arrayToString = new StringBuilder();
        int lenght = Array.getLength(obj);
        for (int i = 0; i < lenght; i++) {
            Object currElement = Array.get(obj, i);
            if (currElement instanceof String) {
                arrayToString.append(getStringStr(currElement));
            } else {
                arrayToString.append(currElement);
            }
            if (i != lenght - 1) arrayToString.append(",");
        }
        return "[" + arrayToString + "]";
    }

    private String getStringStr(Object obj) {
        return "\"" + obj + "\"";
    }

    private String getCollectionString(Object obj) {
        return getArrayStr(((Collection) obj).toArray());
    }
}
