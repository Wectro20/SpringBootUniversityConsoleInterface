package com.andrii.spring_boot_university_console_interface.repository;

import com.andrii.spring_boot_university_console_interface.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  @Query("SELECT d.departmentName FROM Department d")
  List<String> findAllDepartmentNames();
}
