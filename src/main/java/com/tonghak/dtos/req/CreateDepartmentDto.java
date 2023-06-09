package com.tonghak.dtos.req;

import com.tonghak.models.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentDto {
    
    private String name;
    
    private String slug;

    private Company company;
}
