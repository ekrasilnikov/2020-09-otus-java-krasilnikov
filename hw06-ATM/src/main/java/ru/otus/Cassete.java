package ru.otus;

import java.util.Map;
import java.util.TreeMap;

class Cassete {
    private Map<BanknoteEnum, Integer> bnkntMap;

    public Cassete(Map<BanknoteEnum, Integer> bnkntMap) {
        this.bnkntMap = bnkntMap;
    }

    public Map<BanknoteEnum, Integer> getBnkntMap() {
        return new TreeMap<>(this.bnkntMap);
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

    void withdrawBanknote(BanknoteEnum banknoteEnum, int count) {
        int newCount = this.bnkntMap.get(banknoteEnum) - count;
        this.bnkntMap.put(banknoteEnum, newCount);
    }
}


