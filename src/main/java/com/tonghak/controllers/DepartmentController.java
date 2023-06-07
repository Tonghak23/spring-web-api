package com.tonghak.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.dtos.req.CreateDepartmentDto;
import com.tonghak.models.Company;
import com.tonghak.models.Department;
import com.tonghak.repo.CompanyRepository;
import com.tonghak.repo.DepartmentRepository;
import com.tonghak.services.DepartmentService;
import com.tonghak.utils.CreateSlugUrl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DepartmentController {
    
  
    private final DepartmentRepository departmentRepository;

    private final CompanyRepository companyRepository;
    
    private final DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/department/{id}")
    public Optional<Department> findOne(@PathVariable Long id) {
        if(!departmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        return departmentRepository.findById(id);
    }

    @PostMapping("/department/company/{id}")
    public Department create(@Valid @RequestBody Department department, @PathVariable Long id) {

        // Department department = new Department();
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid company id");
        }

        department.setName(department.getName());
        department.setSlug(department.getSlug());
        department.setCompany(company);
        return departmentRepository.save(department);

    }

    @DeleteMapping("/department/{id}")
    public void delete(@PathVariable Long id) {
        if(!departmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }

        departmentRepository.deleteById(id);
    }
}
