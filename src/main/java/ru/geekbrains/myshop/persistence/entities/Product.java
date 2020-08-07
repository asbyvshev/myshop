package ru.geekbrains.myshop.persistence.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Entity-класс для единицы продукции.")
public class Product extends PersistableEntity {

    @ApiModelProperty(required = true, value = "Название продукта")
    private String title;

    @ApiModelProperty(required = true, value = "Описание продукта")
    private String description;

    @ApiModelProperty(required = true, value = "Дата добавления продукта")
    private Date added;

    @ApiModelProperty(required = true, value = "Цена продукта")
    private Double price;

    @ApiModelProperty(required = true, value = "Доступность продукта")
    private boolean available;

    @ApiModelProperty(required = true, value = "Категория продукта")
    @Enumerated(EnumType.ORDINAL)
    private ProductCategory category;

    @ApiModelProperty(reference = "Image", value = "Фотография продукта")
    @OneToOne
    @JoinColumn(name = "image")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_image")
    )
    private List<Image> images;

    @ApiModelProperty(reference = "product", value = "Список отзывов")
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
}