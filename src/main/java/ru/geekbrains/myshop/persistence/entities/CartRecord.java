package ru.geekbrains.myshop.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_record")
@EqualsAndHashCode(callSuper = true)
public class CartRecord extends PersistableEntity {

    private Integer quantity;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private ru.geekbrains.myshop.persistence.entities.Product product;

    @ManyToOne
    @JoinColumn(name = "purchase")
    private Purchase purchase;

    public CartRecord(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }

}