package com.tonghak.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.tonghak.models.Company;
import com.tonghak.repo.CompanyRepository;

public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
