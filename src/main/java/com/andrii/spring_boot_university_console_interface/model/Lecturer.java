package com.andrii.spring_boot_university_console_interface.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "lecturer")
public class Lecturer {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "salary")
  private Float salary;

  @Column(name = "degree")
  @Enumerated(EnumType.STRING)
  private Degree degree;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToMany(cascade = CascadeType.ALL)
  @JsonIgnore
  @JoinTable(
      name = "lecturer_department",
      joinColumns = @JoinColumn(name = "lecturer_id"),
      inverseJoinColumns = @JoinColumn(name = "department_id"))
  private List<Department> departments;
}
