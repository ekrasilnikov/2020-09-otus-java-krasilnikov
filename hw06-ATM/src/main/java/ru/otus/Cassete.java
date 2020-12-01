package ru.otus;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

class Cassete {
    private TreeMap<BanknoteEnum, Integer> bnkntMap;

    public Cassete(TreeMap<BanknoteEnum, Integer> bnkntMap) {
        this.bnkntMap = bnkntMap;
    }

    public Map<BanknoteEnum, Integer> getBnkntMap() {
        Map<BanknoteEnum, Integer> result = new TreeMap<>(Collections.reverseOrder());
        result.putAll(this.bnkntMap);
        return result;
    }

    int getBnkntSum(BanknoteEnum banknoteEnum) {
        return this.bnkntMap.get(banknoteEnum) * banknoteEnum.getDenomination();
    }

    int getCasseteSum() {
        int result = 0;
        for (Map.Entry<BanknoteEnum, Integer> currEntry : this.bnkntMap.entrySet()) {
            result += getBnkntSum(currEntry.getKey());
        }
        return result;
    }

    void addBanknote(BanknoteEnum banknoteEnum, int count) {
        int newCount = this.bnkntMap.get(banknoteEnum) + count;
        this.bnkntMap.put(banknoteEnum, newCount);
    }

    void withdrawBanknote(BanknoteEnum banknoteEnum, int count) throws ATMExceptions {
        if (this.bnkntMap.containsKey(banknoteEnum)) {
            int newCount = this.bnkntMap.get(banknoteEnum) - count;
            this.bnkntMap.put(banknoteEnum, newCount);
        } else throw new ATMExceptions("Banknote not recognized");
    }
}


