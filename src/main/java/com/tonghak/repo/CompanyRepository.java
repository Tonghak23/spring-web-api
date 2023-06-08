package com.tonghak.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonghak.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    
}
