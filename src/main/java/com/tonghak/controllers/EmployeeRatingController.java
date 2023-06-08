package com.tonghak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.models.Employee;
import com.tonghak.models.EmployeeRating;
import com.tonghak.models.Rating;

import com.tonghak.repo.EmployeeRatingRepository;
import com.tonghak.repo.EmployeeRepository;
import com.tonghak.repo.RatingRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeRatingController {

    @Autowired
    private EmployeeRatingRepository employeeRatingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired 
    private RatingRepository ratingRepository;


    @GetMapping("/employee-rating")
    public List<EmployeeRating> findAll() {
        return employeeRatingRepository.findAll();
    }

    @PostMapping("/employee-rating/{employee_id}/{rating_id}")
    public EmployeeRating create(@PathVariable Long employee_id, @PathVariable Long rating_id, @Valid @RequestBody EmployeeRating employeeRating) {

        Employee employee = employeeRepository.findById(employee_id).orElse(null);
        if(employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employee id");
        }

        Rating rating = ratingRepository.findById(employee_id).orElse(null);
        if(rating == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid rating id");
        }

        employeeRating.setEmployee(employee);
        employeeRating.setRating(rating);
        return employeeRatingRepository.save(employeeRating);
        
    }

}
