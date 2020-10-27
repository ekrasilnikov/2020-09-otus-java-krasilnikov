package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestReflectMethods {
    public static void runMethod(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        method.invoke(object);
    }

    public static void runMethod(Object object, List<Method> methodList) throws InvocationTargetException, IllegalAccessException {
        for (Method currentMethod : methodList) {
            currentMethod.invoke(object);
        }
    }

    public static List<Method> getAnnotationsMethod(Class<?> inputClass, Class<? extends Annotation> searchAnnotation) {
        List<Method> result = new ArrayList<>();
        for (Method currentMethod : inputClass.getDeclaredMethods()) {
            if (currentMethod.isAnnotationPresent(searchAnnotation)) {
                result.add(currentMethod);
            }
        }
        return result;
    }
}
