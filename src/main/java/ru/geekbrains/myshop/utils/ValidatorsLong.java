package ru.geekbrains.myshop.utils;



public class ValidatorsLong {
    public static boolean isLongID(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
