package ru.geekbrains.myshop.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.myshop.beans.Cart;
import ru.geekbrains.myshop.persistence.entities.Shopuser;
import ru.geekbrains.myshop.services.ProductService;
import ru.geekbrains.myshop.services.ReviewService;
import ru.geekbrains.myshop.services.ShopuserService;
import ru.geekbrains.myshop.utils.CaptchaGenerator;
import ru.geekbrains.myshop.utils.Validators;
import ru.geekbrains.paymentservice.Payment;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Api(tags = "Набор методов для онлайн-магазина.")
public class ShopController {

    private final Cart cart;
    private final CaptchaGenerator captchaGenerator;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final ShopuserService shopuserService;

    @ApiOperation(value = "Вывод списка продуктов.", response = String.class)
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model,
                        @RequestParam(required = false) Integer category,
                        @RequestParam(required = false) Integer minPrice,
                        @RequestParam(required = false) Integer maxPrice,
                        @RequestParam(required = false) Boolean notAvailable) {

        model.addAttribute("cart", cart.getCartRecords());
        model.addAttribute("products", productService.findAll(category, minPrice, maxPrice, notAvailable));

        return "index";
    }

    @ApiOperation(value = "Админ страница.", response = String.class)
    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {

        if (principal == null) { return "redirect:/"; }

        model.addAttribute("products", productService.findAll());

        return "admin";
    }

    @ApiOperation(value = "Профиль пользователя.", response = String.class)
    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {

        if (principal == null) { return "redirect:/"; }

        Shopuser shopuser = shopuserService.findByPhone(principal.getName());

        model.addAttribute("reviews", reviewService.getReviewsByShopuser(shopuser).orElse(new ArrayList<>()));
        model.addAttribute("shopuser", shopuser);

        return "profile";
    }

    @ApiOperation(value = "Вывод изображения captcha.")
    @GetMapping(value = "/captcha", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] captcha(HttpSession session) {
        try {
            BufferedImage img = captchaGenerator.getCaptchaImage();
            session.setAttribute("captchaCode", captchaGenerator.getCaptchaString());
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img, "png", bao);
            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "Подтверждение заказа.", response = String.class)
    @PostMapping("/checkout")
    public String proceedToCheckout(String paymentId, Model model) {

        Payment payment = cart.getPayments()
                .stream()
                .filter(p -> p.getId() == Integer.valueOf(paymentId))
                .collect(Validators.toSingleton());

        cart.setPayment(payment);

        model.addAttribute("cart", cart);

        return "checkout";
    }
}