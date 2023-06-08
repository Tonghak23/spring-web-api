package com.tonghak.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonghak.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
