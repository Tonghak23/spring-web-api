package com.tonghak.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.dtos.req.CreateEmployeeDto;
import com.tonghak.dtos.req.UpdateEmployeeDto;
import com.tonghak.models.Department;
import com.tonghak.models.Employee;
import com.tonghak.repo.DepartmentRepository;
import com.tonghak.repo.EmployeeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/employee")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }   

    @PostMapping("/employee/department/{id}")
    public Employee create(@PathVariable Long id, @Valid @RequestBody CreateEmployeeDto createEmployeeDto) {

        Employee employee = new Employee();

        Department dept = departmentRepository.findById(id).orElse(null);
        if(dept == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid department id");
        }

        employee.setName(createEmployeeDto.getName());
        employee.setEmail(createEmployeeDto.getEmail());
        employee.setAge(createEmployeeDto.getAge());
        employee.setPosition(createEmployeeDto.getPosition());
        employee.setDepartment(dept);
        return employeeRepository.save(employee);

    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> findOne(@PathVariable Long id) {

        if(!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return employeeRepository.findById(id);
    }

    @PutMapping("/employee/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDto updateEmployeeDto) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        employee.setName(updateEmployeeDto.getName());
        employee.setEmail(updateEmployeeDto.getName());
        employee.setAge(updateEmployeeDto.getAge());
        employee.setPosition(updateEmployeeDto.getPosition());
        employeeRepository.save(employee);

    }

    @PatchMapping("/employee/department/{id}/{employeeId}")
    public void updateEmployeeDepartment(@PathVariable Long id, @PathVariable Long employeeId) {
        Department department = departmentRepository.findById(id).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        employee.setDepartment(department);
        employeeRepository.save(employee);

    }

    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable Long id) {

        if(!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }

        employeeRepository.deleteById(id);
    }
}
