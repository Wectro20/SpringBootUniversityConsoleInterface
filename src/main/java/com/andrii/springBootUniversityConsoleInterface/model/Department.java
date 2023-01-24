package com.andrii.springBootUniversityConsoleInterface.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "department_name")
  private String departmentName;

  @ManyToMany(mappedBy = "departments")
  @JsonIgnore
  private List<Lecturer> lecturers;
}

