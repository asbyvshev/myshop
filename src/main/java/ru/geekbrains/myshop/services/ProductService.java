package ru.geekbrains.myshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.myshop.exceptions.ProductNotFoundException;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findOneById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(
            () -> new ProductNotFoundException("Oops! Product " + id + " wasn't found!")
        );
    }

    public List<Product> findAll(Integer category) {
        return category == null ? productRepository.findAll() : productRepository.findAllByCategory(ProductCategory.values()[category]);
    }

}