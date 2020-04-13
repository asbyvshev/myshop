package ru.geekbrains.myshop.persistence.pojo;


import lombok.Data;

import java.util.UUID;

@Data
public class ReviewPojo {
    private String captchaCode;
    private String commentary;
    private Long productId;
}