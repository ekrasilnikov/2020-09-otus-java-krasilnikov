package ru.otus;

public class Demo {

    public static void main(String[] args) {
        System.out.println("*****Example of annotated methods*****");
        MyClassInterface myClassInterface = MyClassProxy.createMyClass();
        myClassInterface.calculation(90);
        myClassInterface.calculation(183, 12);
        myClassInterface.calculation(11, 3, MyClassEnum.DIVISION);
        System.out.println("*****Example of methods without annotation*****");
        myClassInterface.calculationWOLog(111, 121);
    }
}
