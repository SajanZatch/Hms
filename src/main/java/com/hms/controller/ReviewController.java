package com.hms.controller;


import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;
    private PropertyRepository propertyRepo;

    public ReviewController(ReviewRepository reviewRepo, PropertyRepository propertyRepo) {
        this.reviewRepo = reviewRepo;
        this.propertyRepo = propertyRepo;
    }


    @PostMapping
    public ResponseEntity<?> write(
            @RequestBody Review review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser user
            ){
        Property property = propertyRepo.findById(propertyId).get();

        if(reviewRepo.existsByAppUserAndProperty(user,property)){
            return new ResponseEntity<>("review already exists",HttpStatus.CONFLICT);
        }
        review.setProperty(property);
        review.setAppUser(user);

        Review savedReview = reviewRepo.save(review);

        return new ResponseEntity<>(savedReview, HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/review/user/review
    @GetMapping("/user/review")
    public ResponseEntity<List<Review>> getUserReviews(
            @AuthenticationPrincipal AppUser user
    ){
        List<Review> reviews = reviewRepo.findByAppUser(user);
        return new ResponseEntity(reviews, HttpStatus.OK);
    }

}
