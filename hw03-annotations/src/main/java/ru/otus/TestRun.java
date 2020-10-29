package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRun {

    private Class<?> obtainedClass;
    private List<Method> beforeMethodsList;
    private List<Method> testMethodsList;
    private List<Method> afterMethodsList;

    public TestRun(String className) {
        try {
            setObtainedClass(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setBeforeMethodsList(TestReflectMethods.getAnnotationsMethod(getObtainedClass(), Before.class));
        setTestMethodsList(TestReflectMethods.getAnnotationsMethod(getObtainedClass(), Test.class));
        setAfterMethodsList(TestReflectMethods.getAnnotationsMethod(getObtainedClass(), After.class));
    }

    public Class<?> getObtainedClass() {
        return obtainedClass;
    }

    public void setObtainedClass(Class<?> obtainedClass) {
        this.obtainedClass = obtainedClass;
    }

    public List<Method> getBeforeMethodsList() {
        return beforeMethodsList;
    }

    public void setBeforeMethodsList(List<Method> beforeMethodsList) {
        this.beforeMethodsList = beforeMethodsList;
    }

    public List<Method> getTestMethodsList() {
        return testMethodsList;
    }

    public void setTestMethodsList(List<Method> testMethodsList) {
        this.testMethodsList = testMethodsList;
    }

    public List<Method> getAfterMethodsList() {
        return afterMethodsList;
    }

    public void setAfterMethodsList(List<Method> afterMethodsList) {
        this.afterMethodsList = afterMethodsList;
    }

    public static void main(String[] args) throws Exception {
        TestRun testRun = new TestRun("ru.otus.TestClass");
        testRun.testLauncher();
    }

    public void testLauncher() throws Exception {
        List<TestResultEnum> testResultList = new ArrayList<>();
        for (Method currentMethod : getTestMethodsList()) {
            Object testClassObject = createNewTestObject(getObtainedClass());
            try {
                TestReflectMethods.runMethod(testClassObject, getBeforeMethodsList());
                TestReflectMethods.runMethod(testClassObject, currentMethod);
                TestReflectMethods.runMethod(testClassObject, getAfterMethodsList());
                testResultList.add(TestResultEnum.SUCCESSFUL);
            } catch (Exception e) {
                testResultList.add(TestResultEnum.FAILED);
            }
        }
        printTestResult(testResultList);
    }

    private Object createNewTestObject(Class<?> inputClass) throws Exception {
        return inputClass.getDeclaredConstructor().newInstance();
    }

    private void printTestResult(List<TestResultEnum> result) {
        System.out.println("Test count: " + result.size());
        System.out.println("Test successful: " + getTestCount(result, TestResultEnum.SUCCESSFUL));
        System.out.println("Test failed: " + getTestCount(result, TestResultEnum.FAILED));
    }

    private int getTestCount(List<TestResultEnum> testResult, TestResultEnum searchEnum) {
        return Collections.frequency(testResult, searchEnum);
    }
}