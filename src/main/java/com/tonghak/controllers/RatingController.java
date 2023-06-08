package com.tonghak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonghak.models.Rating;
import com.tonghak.repo.RatingRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;


    @PostMapping("/rating")
    public Rating create(@Valid @RequestBody Rating rating) {
        rating.setRating(rating.getRating());
        return ratingRepository.save(rating);
    }

    @PatchMapping("/rating/{id}")
    public void create(@PathVariable Long id, @RequestBody Rating rate) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        rating.setRating(rate.getRating()); 
        ratingRepository.save(rating);    
    }
}
