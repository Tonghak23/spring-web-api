package com.tonghak.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tonghak.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
