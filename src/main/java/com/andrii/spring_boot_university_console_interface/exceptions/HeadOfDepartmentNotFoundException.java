package com.andrii.spring_boot_university_console_interface.exceptions;

public class HeadOfDepartmentNotFoundException extends RuntimeException{
  public HeadOfDepartmentNotFoundException(String message) {
    super(message);
  }
}
