package ru.geekbrains.myshop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,  Long> {
    List<Product> findAll();
    List<Product> findAllByCategory(ProductCategory category);
}