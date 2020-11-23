package ru.otus;

public class Demo {

    public static void main(String[] args) throws NoSuchMethodException {

        MyClassInterface myClassInterface = MyClassProxy.createMyClass();

        System.out.println("*****Example of annotated methods*****");
        myClassInterface.calculationSum(183, 12);
        System.out.println();

        myClassInterface.calculationOperation(11, 3, MyClassEnum.DIVISION);
        System.out.println();

        System.out.println("*****Example of methods without annotation*****");
        myClassInterface.calculationSumWOLog(111, 121);
    }
}
