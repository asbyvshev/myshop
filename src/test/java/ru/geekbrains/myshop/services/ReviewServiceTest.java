package ru.geekbrains.myshop.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.Review;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.pojo.ProductPojo;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {

        ProductPojo productPojo = ProductPojo.builder()
                .title("Пепси")
                .available(true)
                .category(ProductCategory.DRINK)
                .price(220.0)
                .description("")
                .build();

        productService.save(productPojo, null);
        Product product = productService.findOneByTitle("Пепси");

        Review review = Review.builder()
                .image(null)
                .approved(false)
                .commentary("")
                .product(product)
        .build();

        reviewService.save(review);
    }

    @Test
    public void getReviewsByProductTest(){
      List products = reviewService.getReviewsByProduct(productService.findOneByTitle("Пепси")).get();
        assertEquals(1,products.size());
    }
}
