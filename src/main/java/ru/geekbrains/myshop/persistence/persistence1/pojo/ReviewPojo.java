package ru.geekbrains.myshop.persistence.persistence1.pojo;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewPojo {
    private String captchaCode;
    private String commentary;
    private UUID productId;
}