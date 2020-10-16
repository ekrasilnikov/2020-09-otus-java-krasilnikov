package ru.otus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestDIYarrayList {
    public static void main(String... args) {

        String[] strArray = {"NewStr8", "NewStr4", "NewStr1", "NewStr1000"};
        DIYarrayList<String> diyArrayList1 = fillAndGetNewDIYArrayListStrings(25, 100);
        DIYarrayList<String> diyArrayList2 = fillAndGetNewDIYArrayListStrings(25, 500);

        System.out.println("DIY1: " + diyArrayList1);
        System.out.println("DIY2: " + diyArrayList2);
        System.out.println();

        Collections.copy(diyArrayList1, diyArrayList2);
        System.out.println("Collections.copy (DIY2 to DIY1): " + diyArrayList1);

        Collections.addAll(diyArrayList1, strArray);
        System.out.println("Collections.addAll (DIY1): " + diyArrayList1);

        Collections.sort(diyArrayList1);
        System.out.println("Collections.sort (DIY1): " + diyArrayList1);
    }

    private static DIYarrayList<String> fillAndGetNewDIYArrayListStrings(int countElement, int randomRange) {
        final String STRING_TEXT = "GenStr";
        DIYarrayList<String> resultDIY = new DIYarrayList<>();
        Random random = new Random(countElement);
        for (int i = 0; i < countElement; i++) {
            resultDIY.add(STRING_TEXT + random.nextInt(randomRange));
        }
        return resultDIY;
    }
}
