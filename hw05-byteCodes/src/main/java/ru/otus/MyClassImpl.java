package ru.otus;

public class MyClassImpl implements MyClassInterface {
    @Log
    @Override
    public void calculation(int param1) {
        MyClassEnum operation = MyClassEnum.ADDITION;
        printOperationResult(operation, param1);
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        MyClassEnum operation = MyClassEnum.ADDITION;
        int result = param1 + param2;
        printOperationResult(operation, result);
    }

    @Override
    public void calculationWOLog(int param1, int param2) {
        calculation(param1, param2);
    }

    @Log
    @Override
    public void calculation(int param1, int param2, MyClassEnum operation) {
        double result = 0;
        switch (operation) {
            case ADDITION -> {
                result = param1 + param2;
                break;
            }
            case SUBTRACTION -> {
                result = param1 - param2;
                break;
            }
            case MULTIPLICATION -> {
                result = param1 * param2;
                break;
            }
            case DIVISION -> {
                result = (double) param1 / param2;
                break;
            }
        }
        if (operation.equals(MyClassEnum.DIVISION)) {
            printOperationResult(operation, result, true);
        } else {
            printOperationResult(operation, result);
        }
    }

    public void printOperationResult(MyClassEnum operation, Number result) {
        System.out.println("---Operation: " + operation + " , result = " + result);
    }

    public void printOperationResult(MyClassEnum operation, Number result, Boolean isDivision) {
        if (!isDivision) {
            System.out.println("---Operation: " + operation + " , result = " + result);
        } else {
            System.out.println("---Operation: " + operation + " , result = " + (double) result);
        }
    }
}
