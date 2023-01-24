package com.andrii.spring_boot_university_console_interface.consoleMenu;

import com.andrii.spring_boot_university_console_interface.model.Degree;
import com.andrii.spring_boot_university_console_interface.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandMenu {
  @Autowired
  private LecturerService lecturerService;

  public void handleCommand(String command) {
    Matcher headOfDepartmentMatcher = Pattern.compile("Who is head of department (.*)").matcher(command);
    Matcher statisticsMatcher = Pattern.compile("Show (.*) statistics").matcher(command);
    Matcher averageSalaryMatcher = Pattern.compile("Show the average salary for the department (.*)").matcher(command);
    Matcher employeeCountMatcher = Pattern.compile("Show count of employee for (.*)").matcher(command);
    Matcher globalSearchMatcher = Pattern.compile("Global search by (.*)").matcher(command);

    if (headOfDepartmentMatcher.matches()) {
      String departmentName = headOfDepartmentMatcher.group(1);
      String headOfDepartment = lecturerService.findHeadOfDepartment(departmentName);
      if (lecturerService.isDepartmentNameValid(departmentName)) {
        System.out.println("Head of " + departmentName + " department is " + headOfDepartment + "\n");
      }
    } else if (statisticsMatcher.matches()) {
      String departmentName = statisticsMatcher.group(1);
      Integer assistants = lecturerService.countByDegreeAndDepartment(Degree.ASSISTANT.toString(), departmentName);
      Integer associateProfessors = lecturerService.countByDegreeAndDepartment(Degree.ASSOCIATE_PROFESSOR.toString(), departmentName);
      Integer professors = lecturerService.countByDegreeAndDepartment(Degree.PROFESSOR.toString(), departmentName);
      if (lecturerService.isDepartmentNameValid(departmentName)) {
        System.out.println("assistants - " + assistants);
        System.out.println("associate professors - " + associateProfessors);
        System.out.println("professors - " + professors + "\n");
      }
    } else if (averageSalaryMatcher.matches()) {
      String departmentName = averageSalaryMatcher.group(1);
      Double avgSalary = lecturerService.findAvgSalaryByDepartmentName(departmentName);
      if (lecturerService.isDepartmentNameValid(departmentName)) {
        System.out.println("The average salary of " + departmentName + " is " + avgSalary + "\n");
      }
    } else if (employeeCountMatcher.matches()) {
      String departmentName = employeeCountMatcher.group(1);
      Integer employeeCount = lecturerService.countByDepartmentName(departmentName);
      if (lecturerService.isDepartmentNameValid(departmentName)) {
        System.out.println(employeeCount + "\n");
      }
    } else if (globalSearchMatcher.matches()) {
      String template = globalSearchMatcher.group(1);
      List<String> foundedEmployee = lecturerService.findByTemplate(template);
      System.out.println(foundedEmployee.toString().replace("[", "").replace("]", "").trim() + "\n");
    } else {
      System.out.println("Invalid command. Please try again.");
    }
  }
}


