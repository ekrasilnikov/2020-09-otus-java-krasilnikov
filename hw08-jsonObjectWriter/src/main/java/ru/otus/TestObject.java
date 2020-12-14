package ru.otus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TestObject {
    private byte value1;
    private Integer value2;
    private boolean value3;
    private String value4;
    private float[] value5;
    private String[] value6;
    private List<String> value7;
    private Set<Double> value8;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return value1 == that.value1 &&
                value3 == that.value3 &&
                Objects.equals(value2, that.value2) &&
                Objects.equals(value4, that.value4) &&
                Arrays.equals(value5, that.value5) &&
                Arrays.equals(value6, that.value6) &&
                Objects.equals(value7, that.value7) &&
                Objects.equals(value8, that.value8);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3, value4, value7, value8);
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                ", value3=" + value3 +
                ", value4='" + value4 + '\'' +
                ", value5=" + Arrays.toString(value5) +
                ", value6=" + Arrays.toString(value6) +
                ", value7=" + value7 +
                ", value8=" + value8 +
                '}';
    }

    public static class Builder {
        private TestObject testObject;

        public Builder() {
            testObject = new TestObject();
        }

        public Builder withValue1(byte value1) {
            testObject.value1 = value1;
            return this;
        }

        public Builder withValue2(Integer value2) {
            testObject.value2 = value2;
            return this;
        }

        public Builder withValue3(boolean value3) {
            testObject.value3 = value3;
            return this;
        }

        public Builder withValue4(String value4) {
            testObject.value4 = value4;
            return this;
        }

        public Builder withValue5(float[] value5) {
            testObject.value5 = value5;
            return this;
        }

        public Builder withValue6(String[] value6) {
            testObject.value6 = value6;
            return this;
        }

        public Builder withValue7(List<String> value7) {
            testObject.value7 = value7;
            return this;
        }

        public Builder withValue8(Set<Double> value8) {
            testObject.value8 = value8;
            return this;
        }

        public TestObject build() {
            return testObject;
        }
    }
}
