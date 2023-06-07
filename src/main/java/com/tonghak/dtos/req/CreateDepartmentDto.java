package com.tonghak.dtos.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tonghak.models.Company;


import lombok.Data;

@Data
public class CreateDepartmentDto {

    // @NotEmpty
    // @Size(min = 2, message = "Company name should have at least 2 characters")
    
    private String name;
    
    private String slug;

    private Company company;
    // @JsonProperty(value = "company_id")
    // private long companyId;
}
