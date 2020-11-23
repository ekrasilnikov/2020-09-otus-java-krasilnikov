package ru.otus;

class ATMMessages {

    public void printBalance(int balance) {
        System.out.println("ATM Balance: " + balance + " RUB");
    }

    public void printDepositingSum(int amount) {
        System.out.println("Cash deposit: " + amount + " RUB");
    }

    public void printWithdrawTrying(int amount) {
        System.out.println("Attempt to issue funds: " + amount + " RUB");
    }

    public void printWithdrawSuccess() {
        System.out.println("Funds have been successfully withdrawn");
    }

    public String getWithdrawFail() {
        return "Cash withdrawal is not possible, enter another amount.";
    }
}
