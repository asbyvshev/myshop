package ru.geekbrains.myshop.utils;


import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ValidatorsLong {
    public static boolean isLongID(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
