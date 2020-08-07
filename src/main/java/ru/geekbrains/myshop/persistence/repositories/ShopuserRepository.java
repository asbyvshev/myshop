package ru.geekbrains.myshop.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.myshop.persistence.entities.Shopuser;

import java.util.UUID;

@Repository
public interface ShopuserRepository extends CrudRepository<Shopuser, Long> {
    Shopuser findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}