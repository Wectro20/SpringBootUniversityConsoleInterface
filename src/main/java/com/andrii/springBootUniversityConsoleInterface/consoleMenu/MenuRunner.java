package com.andrii.springBootUniversityConsoleInterface.consoleMenu;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class MenuRunner implements CommandLineRunner {
  private final CommandMenu commandMenu;


  @Override
  public void run(String... args) {
    Scanner scanner = new Scanner(System.in);


    while (true) {
      System.out.println("Commands template");
      String[] commands = {
          "• Who is head of department {department_name}",
          "• Show {department_name} statistics",
          "• Show the average salary for the department {department_name}",
          "• Show count of employee for {department_name}",
          "• Global search by {template}"};
      Arrays.stream(commands).forEachOrdered(System.out::println);
      System.out.println("Enter command: ");
      String command = scanner.nextLine();

      commandMenu.handleCommand(command);
    }
  }
}
