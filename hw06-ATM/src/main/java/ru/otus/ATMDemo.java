package ru.otus;

import java.util.Map;
import java.util.TreeMap;

public class ATMDemo {
    public static void main(String[] args) throws ATMExceptions {
        TreeMap<BanknoteEnum, Integer> banknoteMapInATM = (TreeMap<BanknoteEnum, Integer>) new BanknoteMapBuilder()
                .add50(10)
                .add100(10)
                .add200(10)
                .add500(10)
                .add1000(10)
                .add2000(10)
                .add5000(10)
                .build();

        ATM atm = new ATM(new Cassete(banknoteMapInATM));
        atm.printBalance();

        Map<BanknoteEnum, Integer> banknoteMapDeopsit = new BanknoteMapBuilder()
                .add200(5)
                .add5000(1)
                .build();

        atm.depositingCash(banknoteMapDeopsit);
        atm.printBalance();
        atm.withdrawMoney(12955);
        atm.printBalance();
        atm.withdrawMoney(44000);
        atm.printBalance();
        atm.withdrawMoney(100000);
        atm.printBalance();
        atm.withdrawMoney(44500);
        atm.printBalance();
    }
}
