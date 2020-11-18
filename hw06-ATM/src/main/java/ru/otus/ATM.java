package ru.otus;

import java.util.*;

class ATM {
    private Cassete cassete;

    public ATM(Cassete cassete) {
        this.cassete = cassete;
    }

    public void printBalance() {
        ATMMessage.printBalance(this.cassete.getCasseteSum());
    }

    public void depositingCash(Map<BanknoteEnum, Integer> depositingMap) {
        for (Map.Entry<BanknoteEnum, Integer> currEntry : depositingMap.entrySet()) {
            cassete.addBanknote(currEntry.getKey(), currEntry.getValue());
        }
        ATMMessage.printDepositingSum(getDepositingCashSum(depositingMap));
    }

    private Integer getDepositingCashSum(Map<BanknoteEnum, Integer> depositingMap) {
        int result = 0;
        for (Map.Entry<BanknoteEnum, Integer> currEntry : depositingMap.entrySet()) {
            result += currEntry.getKey().getDenomination() * currEntry.getValue();
        }
        return result;
    }

    public void withdrawMoney(int amount) {
        ATMMessage.printWithdrawTrying(amount);

        Map<BanknoteEnum, Integer> reverseAtmBnkntMap = new TreeMap<>(Collections.reverseOrder());
        reverseAtmBnkntMap.putAll(this.cassete.getBnkntMap());
        WithdrawResult withdrawResult = checkWithdrawPossibility(reverseAtmBnkntMap, amount);

        if (withdrawResult.isReal()) {
            withdrawMoneyFromCassete(withdrawResult.getWithdrawMap());
            ATMMessage.printWithdrawSuccess();
        } else {
            ATMMessage.printWithdrawFail(this.cassete.getCasseteSum());
        }
    }

    private WithdrawResult checkWithdrawPossibility(Map<BanknoteEnum, Integer> map, int amount) {
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
        }
        return new WithdrawResult(isReal, result);
    }

    private void withdrawMoneyFromCassete(Map<BanknoteEnum, Integer> map) {
        for (Map.Entry<BanknoteEnum, Integer> currentEntry : map.entrySet()) {
            this.cassete.withdrawBanknote(currentEntry.getKey(), currentEntry.getValue());
        }
    }


    class WithdrawResult {
        private boolean isReal;
        private Map<BanknoteEnum, Integer> withdrawMap;

        WithdrawResult(boolean isReal, Map<BanknoteEnum, Integer> withdrawMap) {
            this.isReal = isReal;
            this.withdrawMap = withdrawMap;
        }

        boolean isReal() {
            return isReal;
        }

        Map<BanknoteEnum, Integer> getWithdrawMap() {
            return withdrawMap;
        }
    }

}

