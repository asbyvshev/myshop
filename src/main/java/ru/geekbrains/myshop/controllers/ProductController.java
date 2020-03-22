package ru.geekbrains.myshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.myshop.exceptions.ProductNotFoundException;
import ru.geekbrains.myshop.services.ImageService;
import ru.geekbrains.myshop.services.ProductService;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public String getOneProduct(Model model, @PathVariable Long id) throws ProductNotFoundException {

        // TODO ДЗ - утилита, которая будет проверять UUID

        model.addAttribute("product", productService.findOneById(id));
//        System.out.println(productService.findOneById(id).getImages());
        return "product";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long id) {

        // TODO ДЗ - сделать поддержку множества картинок

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(imageService.loadFileAsResource(id), "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}