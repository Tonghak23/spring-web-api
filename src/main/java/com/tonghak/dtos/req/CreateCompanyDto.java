package com.tonghak.dtos.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCompanyDto {

    @NotEmpty
    @Size(min = 2, message = "Company name should have at least 2 characters")
    private String name;
    
    private String slug;
    
}
