package ru.geekbrains.myshop.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.geekbrains.myshop.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Purchase extends PersistableEntity {

    private Double price;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<CartRecord> cartRecords;

    @ManyToOne
    @JoinColumn(name = "shopuser")
    private Shopuser shopuser;

}