package ru.geekbrains.myshop.persistence.entities;

import lombok.*;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends PersistableEntity {

    private String title;

    private String description;

    private Date added;

    private Double price;

    private boolean available;

    @Enumerated(EnumType.ORDINAL)
    private ProductCategory category;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;

}