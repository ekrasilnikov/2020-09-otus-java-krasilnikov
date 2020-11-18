package ru.otus;

import java.util.Map;
import java.util.TreeMap;

public class BanknoteMapBuilder {
    private Map<BanknoteEnum, Integer> banknoteMap;

    public BanknoteMapBuilder() {
        banknoteMap = new TreeMap<>();
        banknoteMap.put(BanknoteEnum.BNKNT50, 0);
        banknoteMap.put(BanknoteEnum.BNKNT100, 0);
        banknoteMap.put(BanknoteEnum.BNKNT200, 0);
        banknoteMap.put(BanknoteEnum.BNKNT500, 0);
        banknoteMap.put(BanknoteEnum.BNKNT1000, 0);
        banknoteMap.put(BanknoteEnum.BNKNT2000, 0);
        banknoteMap.put(BanknoteEnum.BNKNT5000, 0);
    }

    public BanknoteMapBuilder add50(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT50, count);
        return this;
    }

    public BanknoteMapBuilder add100(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT100, count);
        return this;
    }

    public BanknoteMapBuilder add200(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT200, count);
        return this;
    }

    public BanknoteMapBuilder add500(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT500, count);
        return this;
    }

    public BanknoteMapBuilder add1000(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT1000, count);
        return this;
    }

    public BanknoteMapBuilder add2000(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT2000, count);
        return this;
    }

    public BanknoteMapBuilder add5000(int count) {
        banknoteMap.put(BanknoteEnum.BNKNT5000, count);
        return this;
    }

    public Map<BanknoteEnum, Integer> build() {
        return banknoteMap;
    }
}
