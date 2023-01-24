package com.andrii.springBootUniversityConsoleInterface.exceptions;

public class DepartmentNameNotFoundException extends RuntimeException{
  public DepartmentNameNotFoundException(String message) {
    super(message);
  }
}
