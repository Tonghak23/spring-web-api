package com.tonghak.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tonghak.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // public Company findById(Company id);


    
}
