package ru.otus;

public class Demo {

    public static void main(String[] args) {
        MyClassInterface myClassInterface = MyClassProxy.createMyClass();
        myClassInterface.calculation(90);
        myClassInterface.calculation(183, 12);
        myClassInterface.calculation(11, 3, MyClassEnum.DIVISION);
    }
}
