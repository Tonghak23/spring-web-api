package com.tonghak.dtos.req;

import com.tonghak.models.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDto {

    private String name;
    
    private String email;

    private int age;

    private String position;

    private Department department;
}
