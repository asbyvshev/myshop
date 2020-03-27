package ru.geekbrains.myshop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.geekbrains.myshop.exceptions.ProductNotFoundException;
import ru.geekbrains.myshop.persistence.entities.Image;
import ru.geekbrains.myshop.persistence.entities.Product;
import ru.geekbrains.myshop.persistence.entities.Review;
import ru.geekbrains.myshop.persistence.entities.Shopuser;
import ru.geekbrains.myshop.persistence.pojo.ProductPojo;
import ru.geekbrains.myshop.persistence.pojo.ReviewPojo;
import ru.geekbrains.myshop.services.ImageService;
import ru.geekbrains.myshop.services.ProductService;
import ru.geekbrains.myshop.services.ReviewService;
import ru.geekbrains.myshop.services.ShopuserService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final ShopuserService shopuserService;

    @GetMapping("/{id}")
    public String getOneProduct(Model model, @PathVariable String id) throws ProductNotFoundException {

        Product product = productService.findOneById(Long.parseLong(id));
        List<Review> reviews = reviewService.getReviewsByProduct(product).orElse(new ArrayList<>());
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "product";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String id) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = imageService.loadFileAsResource(id);
        if (bufferedImage != null) {
            ImageIO.write(bufferedImage,"png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } else {
            return new byte[0];
        }
    }

    @PostMapping
    public String addOne(@RequestParam("image") MultipartFile image, ProductPojo productPojo) throws IOException {
        Image img = imageService.uploadImage(image, productPojo.getTitle());
        return productService.save(productPojo, img);
    }

    @PostMapping("/reviews")
    public String addReview(ReviewPojo reviewPojo, HttpSession session, Principal principal) throws ProductNotFoundException {

        Product product = productService.findOneById(reviewPojo.getProductId());
        Shopuser shopuser = shopuserService.findByPhone(principal.getName());

        Review review = Review.builder()
                .commentary(reviewPojo.getCommentary())
                .product(product)
                .shopuser(shopuser)
                .build();

        reviewService.save(review);

        return "redirect:/products/" + product.getId();

    }

}