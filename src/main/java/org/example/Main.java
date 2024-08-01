package org.example;

import static org.example.PairUtil.concatOrSum;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, Integer> intPair = new Pair<>(3, 5);
        Pair<Double, Double> doublePair = new Pair<>(2.5, 3.5);
        Pair<String, String> stringPair = new Pair<>("Привет", "Олег");

        System.out.println("Сумма целых чисел: " + concatOrSum(intPair));
        System.out.println("Сумма дробных числе: " + concatOrSum(doublePair));
        System.out.println("Текст из объединенных строк: " + concatOrSum(stringPair));
    }
}
