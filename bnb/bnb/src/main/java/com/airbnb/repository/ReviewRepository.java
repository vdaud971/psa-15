package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("Select r from Review r where r.property=:property and r.appUser=:appUser")
     Review findByUserAndProperty(
             @Param("property")Property property,
             @Param("appUser")AppUser appUser
    );


    @Query("select r from Review r where r.appUser=:appUser")
    List<Review> findReviewsByParticularUser(
       @Param("appUser")AppUser appUser
    );
}