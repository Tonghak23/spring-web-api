package com.tonghak.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
@Table(name = "company")
public class Company {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
	private Long id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Column(name = "slug")
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String slug;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    @JsonIgnore
    private List<Department> departments;

    public Company(Long id, String name, String slug, List<Department> departments) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.departments = departments;
    }
    public Company() {
      
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    
    

}
//https://github.com/ErgunAhmet/DataJpaYt