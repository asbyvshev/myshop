package ru.geekbrains.myshop.persistence.entities.utils;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class PersistableEntity implements Serializable {

    @Id
    private Long id;

}
