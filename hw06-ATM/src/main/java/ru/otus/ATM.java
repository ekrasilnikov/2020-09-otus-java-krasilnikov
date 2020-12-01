package ru.otus;

import java.util.*;

class ATM {
    private final Cassete cassete;
    private final ATMMessages atmMessages;

    {
        atmMessages = new ATMMessages();
    }

    public ATM(Cassete cassete) {
        this.cassete = cassete;
    }

    public void printBalance() {
        this.atmMessages.printBalance(this.cassete.getCasseteSum());
    }

    public void depositingCash(Map<BanknoteEnum, Integer> depositingMap) {
        for (Map.Entry<BanknoteEnum, Integer> currEntry : depositingMap.entrySet()) {
            cassete.addBanknote(currEntry.getKey(), currEntry.getValue());
        }
        this.atmMessages.printDepositingSum(getDepositingCashSum(depositingMap));
    }

    private Integer getDepositingCashSum(Map<BanknoteEnum, Integer> depositingMap) {
        int result = 0;
        for (Map.Entry<BanknoteEnum, Integer> currEntry : depositingMap.entrySet()) {
            result += currEntry.getKey().getDenomination() * currEntry.getValue();
        }
        return result;
    }

    public void withdrawMoney(int amount) {
        this.atmMessages.printWithdrawTrying(amount);
        try {
            withdrawMoneyFromCassete(checkWithdrawPossibility(this.cassete.getBnkntMap(), amount));
            this.atmMessages.printWithdrawSuccess();
        } catch (ATMExceptions exceptions) {
            System.out.println(exceptions);
        }
    }

    private Map<BanknoteEnum, Integer> checkWithdrawPossibility(Map<BanknoteEnum, Integer> map, int amount) throws ATMExceptions {
        boolean isReal = false;
        Map<BanknoteEnum, Integer> result = new HashMap<>();
        for (Map.Entry<BanknoteEnum, Integer> current : map.entrySet()) {
            int count = amount / current.getKey().getDenomination();
            if (current.getValue() - count >= 0) {
                amount -= current.getKey().getDenomination() * count;
                result.put(current.getKey(), count);
            } else {
                count = current.getValue();
                amount -= current.getKey().getDenomination() * count;
                result.put(current.getKey(), current.getValue());
            }
        }
        if (amount == 0) {
            isReal = true;
        } else {
            throw new ATMExceptions(atmMessages.getWithdrawFail());
        }
        return result;
    }

    private void withdrawMoneyFromCassete(Map<BanknoteEnum, Integer> map) throws ATMExceptions {
        for (Map.Entry<BanknoteEnum, Integer> currentEntry : map.entrySet()) {
            this.cassete.withdrawBanknote(currentEntry.getKey(), currentEntry.getValue());
        }
    }
}

