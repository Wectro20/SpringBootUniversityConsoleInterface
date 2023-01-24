package com.andrii.spring_boot_university_console_interface.exceptions;

public class DepartmentNameNotFoundException extends RuntimeException{
  public DepartmentNameNotFoundException(String message) {
    super(message);
  }
}
