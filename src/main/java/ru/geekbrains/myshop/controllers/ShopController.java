package ru.geekbrains.myshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.myshop.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
//это не JSON
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model, @RequestParam(required = false) Integer category) {

        //TODO сделать фильтр, который будет выводить фильтровать продукты по доступности.

        model.addAttribute("products", productService.findAll(category));
        return "index";
    }

}