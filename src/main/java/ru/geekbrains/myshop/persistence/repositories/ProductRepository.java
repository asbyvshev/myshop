package ru.geekbrains.myshop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myshop.persistence.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,  Long> {
    List<Product> findAll();
}