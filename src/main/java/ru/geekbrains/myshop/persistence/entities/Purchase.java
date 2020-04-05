package ru.geekbrains.myshop.persistence.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Entity-класс для хранения заказа.")
public class Purchase extends PersistableEntity {

    @ApiModelProperty(required = true, value = "Цена")
    private Double price;
    @ApiModelProperty(required = true, value = "Адрес")
    private String address;
    @ApiModelProperty(required = true, value = "Номер телефона")
    private String phone;

    @ApiModelProperty(reference ="purchase", value = "Список товаров в корзине.")
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<CartRecord> cartRecords;

    @ApiModelProperty(reference ="shopuser", value = "Пользователь.")
    @ManyToOne
    @JoinColumn(name = "shopuser")
    private Shopuser shopuser;

}