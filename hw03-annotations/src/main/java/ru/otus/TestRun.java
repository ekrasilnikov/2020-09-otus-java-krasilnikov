package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRun {
    private static Class<?> obtainedClass;
    private static List<Method> beforeMethodsList;
    private static List<Method> testMethodsList;
    private static List<Method> afterMethodsList;
    private static List<TestResultEnum> testResultList;

    public static void main(String[] args) throws Exception {
        preparationForLaunch("ru.otus.TestClass");
        testLauncher();
    }

    private static void preparationForLaunch(String className) throws ClassNotFoundException {
        obtainedClass = Class.forName(className);
        beforeMethodsList = TestReflectMethods.getAnnotationsMethod(obtainedClass, Before.class);
        testMethodsList = TestReflectMethods.getAnnotationsMethod(obtainedClass, Test.class);
        afterMethodsList = TestReflectMethods.getAnnotationsMethod(obtainedClass, After.class);
        testResultList = new ArrayList<>();
    }

    public static void testLauncher() throws Exception {
        for (Method currentMethod : testMethodsList) {
            Object testClassObject = createNewTestObject(obtainedClass);
            try {
                TestReflectMethods.runMethod(testClassObject, beforeMethodsList);
                TestReflectMethods.runMethod(testClassObject, currentMethod);
                TestReflectMethods.runMethod(testClassObject, afterMethodsList);
                testResultList.add(TestResultEnum.SUCCESSFUL);
            } catch (Exception e) {
                testResultList.add(TestResultEnum.FAILED);
            }
        }
        printTestResult(testResultList);
    }

    private static Object createNewTestObject(Class<?> inputClass) throws Exception {
        return inputClass.getDeclaredConstructor().newInstance();
    }

    private static void printTestResult(List<TestResultEnum> result) {
        System.out.println("Test count: " + result.size());
        System.out.println("Test successful: " + getTestCount(result, TestResultEnum.SUCCESSFUL));
        System.out.println("Test failed: " + getTestCount(result, TestResultEnum.FAILED));
    }

    private static int getTestCount(List<TestResultEnum> testResult, TestResultEnum searchEnum) {
        return Collections.frequency(testResult, searchEnum);
    }
}