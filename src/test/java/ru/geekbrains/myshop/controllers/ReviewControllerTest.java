package ru.geekbrains.myshop.controllers;

import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.myshop.persistence.entities.Review;
import ru.geekbrains.myshop.services.ImageService;
import ru.geekbrains.myshop.services.ReviewService;
//import ru.geekbrains.myshop.services.feign.clients.ShopFeignClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    private ReviewController reviewController;

    private List<Review> reviews = new ArrayList<Review>() {{
        add(new Review());
        add(new Review());
        add(new Review());
        add(new Review());
    }};

    @Before
    public void setUp() {

//        ShopFeignClient shopFeignClient = mock(ShopFeignClient.class);
        ReviewService reviewServiceMock = mock(ReviewService.class);
        ImageService imageService = mock(ImageService.class);

        reviewController = new ReviewController(reviewServiceMock, imageService);

        when(reviewServiceMock.getAll()).thenReturn(reviews);

    }

    @Test
    public void getReviewsTest() throws Exception {
        List<Review> testReviews = reviewController.getAllReviews().getBody();
        assertEquals(4, testReviews.size());
    }


}
