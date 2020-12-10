package ru.otus;

import com.google.gson.Gson;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        byte value1 = (byte) 125;
        Integer value2 = 12345;
        boolean value3 = true;
        String value4 = "Str";
        float[] value5 = {1.1f, 2.2f, 3.3f};
        String[] value6 = {"AStr1", "AStr2", "AStr3"};
        List<String> value7 = List.of("one", "two", "three");
        Set<Double> value8 = Set.of(11.222d, 33.567d, 9876.01d);

        TestObject obj = new TestObject.Builder()
                .withValue1(value1)
                .withValue2(value2)
                .withValue3(value3)
                .withValue4(value4)
                .withValue5(value5)
                .withValue6(value6)
                .withValue7(value7)
                .withValue8(value8)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println("  GSON: " + json);

        MyGson myGson = new MyGson();
        String myJson = myGson.toJson(obj);
        System.out.println("MYGSON: " + myJson);

        TestObject obj2 = gson.fromJson(myJson, TestObject.class);
        System.out.println("Equals result: " + obj.equals(obj2));
    }
}
