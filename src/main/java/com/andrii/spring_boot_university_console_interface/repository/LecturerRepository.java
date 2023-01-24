package com.andrii.spring_boot_university_console_interface.repository;

import com.andrii.spring_boot_university_console_interface.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
  @Query(value =
      "SELECT CONCAT(l.first_name, ' ', l.last_name) FROM lecturer l " +
          "JOIN lecturer_department ld ON l.id = ld.lecturer_id " +
          "JOIN department d ON ld.department_id = d.id " +
          "WHERE d.department_name = :department_name AND l.role = 'HEAD'", nativeQuery = true)
  String findHeadByDepartment(@Param("department_name") String departmentName);

  @Query(value =
      "SELECT COUNT(DISTINCT l.id) FROM lecturer l " +
          "JOIN lecturer_department ld ON l.id = ld.lecturer_id " +
          "JOIN department d ON ld.department_id = d.id " +
          "WHERE l.degree = :degree AND d.department_name = :department_name", nativeQuery = true)
  Integer countByDegreeAndDepartment(@Param("degree") String degree, @Param("department_name") String departmentName);

  @Query(value =
      "SELECT AVG(l.salary) FROM lecturer l " +
          "JOIN lecturer_department ld ON l.id = ld.lecturer_id " +
          "JOIN department d ON ld.department_id = d.id " +
          "WHERE d.department_name = :department_name", nativeQuery = true)
  Double findAvgSalaryByDepartmentName(@Param("department_name") String departmentName);

  @Query(value =
      "SELECT COUNT(DISTINCT l.id) FROM lecturer l " +
          "JOIN lecturer_department ld ON l.id = ld.lecturer_id " +
          "JOIN department d ON ld.department_id = d.id " +
          "WHERE d.department_name = :department_name", nativeQuery = true)
  Integer countByDepartmentName(@Param("department_name") String departmentName);

  @Query(value =
      "SELECT CONCAT(l.first_name, ' ', l.last_name) FROM lecturer l " +
          "WHERE CONCAT(l.first_name, ' ', l.last_name) LIKE %:template%", nativeQuery = true)
  List<String> globalSearch(@Param("template") String template);
}
