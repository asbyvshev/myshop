package ru.geekbrains.myshop.persistence.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.myshop.persistence.entities.enums.Role;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Entity-класс для хранения пользователя.")
public class Shopuser extends PersistableEntity {

    @ApiModelProperty(required = true, value = "Номер телефона(логин).")
    private String phone;

    @ApiModelProperty(required = true, value = "Пароль.")
    private String password;

    @ApiModelProperty(required = true, value = "Имя.")
    private String firstName;

    @ApiModelProperty(required = true, value = "Фамилия.")
    private String lastName;

    @ApiModelProperty(required = true, value = "Email.")
    private String email;

    @ApiModelProperty(required = true, value = "Роль пользователя.")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ApiModelProperty(reference ="shopuser", value = "Список заказов.")
    @OneToMany(mappedBy = "shopuser")
    private List<Purchase> purchases;

    @ApiModelProperty(reference ="shopuser" , value = "Список отзывов.")
    @OneToMany(mappedBy = "shopuser")
    private List<Review> reviews;
}