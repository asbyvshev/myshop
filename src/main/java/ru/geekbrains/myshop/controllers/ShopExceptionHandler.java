package ru.geekbrains.myshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.geekbrains.myshop.exceptions.InternalServiceErrorExeption;
import ru.geekbrains.myshop.exceptions.ProductNotFoundException;

@Slf4j
@ControllerAdvice
public class ShopExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex) {
        log.error("Product not found thrown", ex);
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServiceErrorExeption.class)
    public String handleInternalServiceErrorExeption(final InternalServiceErrorExeption ex) {
        log.error("500 Internal Service Error thrown", ex);
        return "error500";
    }

}