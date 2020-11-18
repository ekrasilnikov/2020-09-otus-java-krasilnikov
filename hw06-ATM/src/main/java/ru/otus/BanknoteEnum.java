package ru.otus;

public enum BanknoteEnum {
    BNKNT50(50),
    BNKNT100(100),
    BNKNT200(200),
    BNKNT500(500),
    BNKNT1000(1000),
    BNKNT2000(2000),
    BNKNT5000(5000);

    private int denomination;

    BanknoteEnum(int value) {
        this.denomination = value;
    }

    public int getDenomination() {
        return denomination;
    }
}
