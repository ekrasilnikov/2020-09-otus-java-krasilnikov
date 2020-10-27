package ru.otus;

public class TestClass {

    public TestClass() {
    }

    @Before
    public void testBefore() {
    }

    @Test
    public void test1() {
    }

    @Test
    public void test2() {
        throw new RuntimeException();
    }

    @Test
    public void test3() {
    }

    @Test
    public void test4() {
    }

    @Test
    public void test5() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @After
    public void testAfter() {
    }
}
