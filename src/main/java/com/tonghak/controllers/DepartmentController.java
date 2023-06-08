package com.tonghak.controllers;

import java.util.List;
import java.util.Optional;

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

import com.tonghak.dtos.req.CreateDepartmentDto;
import com.tonghak.dtos.req.UpdateDepartmentDto;
import com.tonghak.models.Company;
import com.tonghak.models.Department;
import com.tonghak.repo.CompanyRepository;
import com.tonghak.repo.DepartmentRepository;
import com.tonghak.utils.CreateSlugUrl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    
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
    public Department create(@PathVariable Long id, @Valid @RequestBody CreateDepartmentDto createDepartmentDto) {

        Department department = new Department();
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid company id");
        }

        department.setName(createDepartmentDto.getName());
        department.setSlug(createDepartmentDto.getSlug());
        department.setCompany(company);
        return departmentRepository.save(department);

    }

    @PutMapping("/department/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateDepartmentDto updateDepartmentDto) {

        Department dept = departmentRepository.findById(id).orElse(null);
        dept.setName(updateDepartmentDto.getName());
        dept.setSlug(updateDepartmentDto.getSlug());
        departmentRepository.save(dept);
    
    }

    @DeleteMapping("/department/{id}")
    public void delete(@PathVariable Long id) {

        if(!departmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }

        departmentRepository.deleteById(id);
    }
}
