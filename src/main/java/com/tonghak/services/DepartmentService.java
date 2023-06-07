package com.tonghak.services;

import org.springframework.http.HttpStatus;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.models.Company;
import com.tonghak.models.Department;
import com.tonghak.repo.CompanyRepository;
import com.tonghak.repo.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public Department createDepartment(Department department, Long id) {

        Company company = companyRepository.findById(id).orElse(null);
        if(company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid company id");
        }

        company.setName(department.getCompany().getName());
        department.setCompany(company);
        return departmentRepository.save(department);
        // return null;
    }
}
