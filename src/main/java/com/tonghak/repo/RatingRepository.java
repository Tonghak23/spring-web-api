package com.tonghak.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonghak.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    
}
