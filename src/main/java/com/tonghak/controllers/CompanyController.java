package com.tonghak.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.dtos.req.CreateCompanyDto;
import com.tonghak.models.Company;
import com.tonghak.repo.CompanyRepository;
import com.tonghak.services.CompanyService;
import com.tonghak.utils.CreateSlugUrl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    private CreateSlugUrl createSlugUrl;
    private CompanyService companyService;

    @GetMapping("/company")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }   

    @PostMapping("/company")
    public Company create(@Valid @RequestBody Company company) {

        company.setName(company.getName());
        company.setSlug(createSlugUrl.urlSlug(company.getName()));

        return companyRepository.save(company);

    }

    @GetMapping("/company/{id}")
    public Optional<Company> findOne(@PathVariable Long id) {
        if(!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        return companyRepository.findById(id);
    }

    @PutMapping("/company/{id}")
    public Company update(@Valid @RequestBody Company company, @PathVariable Long id) {
        if(!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        
        return companyRepository.save(company);
    }

    @DeleteMapping("/company/{id}")
    public void delete(@PathVariable Long id) {
        if(!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }

        companyRepository.deleteById(id);
    }
}
