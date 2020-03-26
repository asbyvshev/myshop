package ru.geekbrains.myshop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myshop.exceptions.ProductNotFoundException;
import ru.geekbrains.myshop.persistence.entities.Image;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.pojo.ProductPojo;
import ru.geekbrains.myshop.persistence.repositories.ProductRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
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

    public List<Product> findAll(Boolean available) {
        return available == null ? productRepository.findAll() : productRepository.findAllByAvailable(available);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAll(Integer category, Boolean available){
        return productRepository.findAllByCategoryAndAvailable(ProductCategory.values()[category], available);
    }

    @Transactional
    public String save(ProductPojo productPogo, Image image) {

        Product product = Product.builder()
                .added(new Date())
                .title(productPogo.getTitle())
                .description(productPogo.getDescription())
                .price(productPogo.getPrice())
                .available(productPogo.isAvailable())
                .category(productPogo.getCategory())
                .image(image)
                .build();

        productRepository.save(product);
        log.info("New Product has been succesfully added! {}", product);
        return "redirect:/";
    }
}