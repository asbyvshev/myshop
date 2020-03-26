package ru.geekbrains.myshop.persistence.pojo;

import lombok.Data;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;

@Data
public class ProductPojo {
    private String title;
    private String description;
    private Double price;
    private boolean available;
    private ProductCategory category;
}
