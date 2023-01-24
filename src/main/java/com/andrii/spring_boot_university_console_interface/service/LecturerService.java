package com.andrii.spring_boot_university_console_interface.service;

import com.andrii.spring_boot_university_console_interface.exceptions.DepartmentNameNotFoundException;
import com.andrii.spring_boot_university_console_interface.exceptions.HeadOfDepartmentNotFoundException;
import com.andrii.spring_boot_university_console_interface.repository.DepartmentRepository;
import com.andrii.spring_boot_university_console_interface.repository.LecturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(LecturerService.class);
  @Autowired
  public LecturerRepository lecturerRepository;
  @Autowired
  public DepartmentRepository departmentRepository;

  public String findHeadOfDepartment(String departmentName) {
    try {
      String head = lecturerRepository.findHeadByDepartment(departmentName);
      if (head == null) {
        throw new HeadOfDepartmentNotFoundException("Head of department " + departmentName + " does not exist");
      }
      return head;
    } catch (HeadOfDepartmentNotFoundException e) {
      LOGGER.error("Error while trying to find head of department", e);
      return "Head of department " + departmentName + " does not exist";
    }
  }

  public Integer countByDegreeAndDepartment(String degree, String departmentName) {
    validateDepartmentName(departmentName);
    return lecturerRepository.countByDegreeAndDepartment(degree, departmentName);
  }

  public Double findAvgSalaryByDepartmentName(String departmentName) {
    validateDepartmentName(departmentName);
    return lecturerRepository.findAvgSalaryByDepartmentName(departmentName);
  }

  public Integer countByDepartmentName(String departmentName) {
    validateDepartmentName(departmentName);
    return lecturerRepository.countByDepartmentName(departmentName);
  }

  public List<String> findByTemplate(String template) {
    List<String> result = lecturerRepository.globalSearch(template);
    try {
      if (result.isEmpty()) {
        throw new EntityNotFoundException("Cant find lecturers by this template: " + template);
      } else {
        return result;
      }
    } catch (EntityNotFoundException e) {
      LOGGER.error("Error while trying to find lecturers by template", e);
      return result;
    }
  }

  public void validateDepartmentName(String departmentName) {
    try {
      List<String> departmentNames = departmentRepository.findAllDepartmentNames();
      if (!departmentNames.contains(departmentName)) {
        throw new DepartmentNameNotFoundException("Department with name " + departmentName + " does not exists");
      }
    } catch (DepartmentNameNotFoundException e) {
      LOGGER.error("Error while trying to find department", e);
    }
  }

  public boolean isDepartmentNameValid(String departmentName) {
    List<String> departmentNames = departmentRepository.findAllDepartmentNames();
    return departmentNames.contains(departmentName);
  }
}
