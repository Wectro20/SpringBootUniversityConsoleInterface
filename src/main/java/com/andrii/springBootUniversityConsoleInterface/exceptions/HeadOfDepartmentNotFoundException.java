package com.andrii.springBootUniversityConsoleInterface.exceptions;

public class HeadOfDepartmentNotFoundException extends RuntimeException{
  public HeadOfDepartmentNotFoundException(String message) {
    super(message);
  }
}
