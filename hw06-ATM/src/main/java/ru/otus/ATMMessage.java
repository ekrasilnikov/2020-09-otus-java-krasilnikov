package ru.otus;

abstract class ATMMessage {

    public static void printBalance(int balance) {
        System.out.println("ATM Balance: " + balance + " RUB");
    }

    public static void printDepositingSum(int amount) {
        System.out.println("Cash deposit: " + amount + " RUB");
    }

    public static void printWithdrawTrying(int amount) {
        System.out.println("Attempt to issue funds: " + amount + " RUB");
    }

    public static void printWithdrawSuccess() {
        System.out.println("Funds have been successfully withdrawn");
    }

    public static void printWithdrawFail(int balance) {
        System.out.println("Cash withdrawal is not possible, enter another amount.");
    }
}
