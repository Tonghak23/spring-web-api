package com.tonghak.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonghak.models.EmployeeRating;

public interface EmployeeRatingRepository extends JpaRepository<EmployeeRating, Long> {
    
}
