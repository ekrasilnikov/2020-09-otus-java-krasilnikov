package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class TestRun {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        testLauncher("ru.otus.TestClass");
    }

    public static void testLauncher(String className) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Class<?> obtainedClass = Class.forName(className);
        ArrayList<Method> beforeMethodsList = getAnnotationsMethod(obtainedClass, Before.class);
        ArrayList<Method> testMethodsList = getAnnotationsMethod(obtainedClass, Test.class);
        ArrayList<Method> afterMethodsList = getAnnotationsMethod(obtainedClass, After.class);
        ArrayList<TestResultEnum> testResultList = new ArrayList<>();

        for (Method currentMethod : testMethodsList
        ) {
            Object testClassObject = createNewTestObject(obtainedClass);
            try {
                runMethod(testClassObject, beforeMethodsList);
                runMethod(testClassObject, currentMethod);
                runMethod(testClassObject, afterMethodsList);
                testResultList.add(TestResultEnum.SUCCESSFUL);
            } catch (Exception e) {
                testResultList.add(TestResultEnum.FAILED);
            }
        }
        printTestResult(testResultList);
    }

    private static Object createNewTestObject(Class<?> inputClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return inputClass.getDeclaredConstructor().newInstance();
    }

    private static void runMethod(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        method.invoke(object);
    }

    private static void runMethod(Object object, ArrayList<Method> methodList) throws InvocationTargetException, IllegalAccessException {
        for (Method currentMethod : methodList
        ) {
            currentMethod.invoke(object);
        }
    }

    private static ArrayList<Method> getAnnotationsMethod(Class inputClass, Class<? extends Annotation> searchAnnotation) {
        ArrayList<Method> result = new ArrayList<>();
        for (Method currentMethod : inputClass.getDeclaredMethods()
        ) {
            if (currentMethod.isAnnotationPresent(searchAnnotation)) {
                result.add(currentMethod);
            }
        }
        return result;
    }

    private static void printTestResult(ArrayList<TestResultEnum> result) {
        System.out.println("Test count: " + result.size());
        System.out.println("Test successful: " + getTestCount(result, TestResultEnum.SUCCESSFUL));
        System.out.println("Test failed: " + getTestCount(result, TestResultEnum.FAILED));
    }

    private static int getTestCount(ArrayList<TestResultEnum> testResult, TestResultEnum searchEnum) {
        return Collections.frequency(testResult, searchEnum);
    }
}