package ru.geekbrains.myshop.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.Review;
import ru.geekbrains.myshop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.myshop.persistence.pojo.ProductPojo;
import ru.geekbrains.myshop.services.ImageService;
import ru.geekbrains.myshop.services.ProductService;
import ru.geekbrains.myshop.services.ReviewService;
import ru.geekbrains.myshop.services.ShopuserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMVCPostTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;
    @MockBean
    private ProductService productService;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private ShopuserService shopuserService;

    @Before
    public void setUp() {

        ProductPojo productPojo = ProductPojo.builder()
                .title("Пепси")
                .available(true)
                .category(ProductCategory.DRINK)
                .price(220.0)
                .description("")
                .build();

        given(productService.save(productPojo,null)).willReturn(String.valueOf(true));

    }
}
