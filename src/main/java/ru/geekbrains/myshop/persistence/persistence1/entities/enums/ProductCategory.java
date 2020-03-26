package ru.geekbrains.myshop.persistence.persistence1.entities.enums;

import lombok.Getter;

public enum ProductCategory {

    DRINK("Напитки"),
    FOOD("Продукты");

    @Getter
    private String name;

    ProductCategory(String name) {
        this.name = name;
    }

}