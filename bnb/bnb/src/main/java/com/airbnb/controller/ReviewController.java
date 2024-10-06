package com.airbnb.controller;


import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.exception.UserExists;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/createreview")
    public ResponseEntity<?> createReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam long propertyId
    ){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        if(byId.isPresent()){
            Property property = byId.get();
            Review reviewDetails = reviewRepository.findByUserAndProperty(property,appUser);
            if(reviewDetails!=null){
                return new ResponseEntity<>("Review Exists",HttpStatus.CREATED);
            }
            review.setAppUser(appUser);
            review.setProperty(property);
        }else{
            throw new UserExists("Id is not present with AppUser "+propertyId);
        }

        Review savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview,HttpStatus.CREATED);
    }

    @GetMapping("/userreviews")
    ResponseEntity<List<Review>> listAllReviewsOfParticularUser(
            @AuthenticationPrincipal AppUser appUser
    ){

        List<Review> allReviews = reviewRepository.findReviewsByParticularUser(appUser);
        if(allReviews==null){

            throw new UserExists("Review does not exists by this User id "+appUser.getId());

        }
        return  new ResponseEntity<>(allReviews,HttpStatus.CREATED);
    }
}
