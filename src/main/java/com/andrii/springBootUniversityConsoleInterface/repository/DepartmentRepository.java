package com.andrii.springBootUniversityConsoleInterface.repository;

import com.andrii.springBootUniversityConsoleInterface.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  @Query("SELECT d.departmentName FROM Department d")
  List<String> findAllDepartmentNames();
}
