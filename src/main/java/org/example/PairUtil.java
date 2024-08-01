package org.example;

public class PairUtil {
    public static <T, U> Object concatOrSum(Pair<T, U> pair) {
        T first = pair.getFirst();
        U second = pair.getSecond();

        if (first instanceof Number && second instanceof Number) {
            Number num1 = (Number) first;
            Number num2 = (Number) second;

            if (num1 instanceof Double || num2 instanceof Double) {
                return num1.doubleValue() + num2.doubleValue();
            } else if (num1 instanceof Float || num2 instanceof Float) {
                return num1.floatValue() + num2.floatValue();
            } else if (num1 instanceof Long || num2 instanceof Long) {
                return num1.longValue() + num2.longValue();
            } else {
                return num1.intValue() + num2.intValue();
            }
        } else if (first instanceof String && second instanceof String) {
            return (String) first + (String) second;
        } else {
            throw new IllegalArgumentException
                    ("Разыне типы данных");
        }
    }
}

