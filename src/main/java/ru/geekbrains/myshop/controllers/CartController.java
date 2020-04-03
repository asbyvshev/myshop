package ru.geekbrains.myshop.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.myshop.beans.Cart;
import ru.geekbrains.myshop.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Api(tags = "Набор методов для корзины.")
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    @ApiOperation(value = "Добавление продукта в корзину.")
    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        cart.add(productService.findOneById(id));
        response.sendRedirect(request.getHeader("referer"));
    }

    @ApiOperation(value = "Удаление продукта из корзины.", response = String.class)
    @GetMapping("/remove/{id}")
    public String removeProductFromCart(@PathVariable Long id) {
        cart.removeByProductId(id);
        return "redirect:/cart";
    }

    @ApiOperation(value = "Список продуктов в корзине.", response = String.class)
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

}