package ru.geekbrains.myshop.persistence.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends PersistableEntity {

    @ApiModelProperty(required = true, value = "Текст отзыва.")
    private String commentary;

    @ApiModelProperty(reference ="shopuser", value = "Пользователь.")
    @ManyToOne
    @JoinColumn(name = "shopuser")
    private Shopuser shopuser;

    @ApiModelProperty(reference ="product", value = "Продукт.")
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @ApiModelProperty(required = true, value = "Одобрен/отклонен")
    private boolean approved;

    @ApiModelProperty(reference ="image", value = "Изображение.")
    @OneToOne
    @JoinColumn(name = "image")
    private Image image;
}
