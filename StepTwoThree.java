/*
*Claudio Palomares
*Comprehensive Lab Two: Step Two and Three - Search filtering and vehicle selection
*/

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class StepTwoThree{
  public static void main(String[] args){
    stepTwoThree();
  }

  public static double stepTwoThree() throws InputMismatchException{
      int carID;
      int carMatches = 0;
      final int csvFields = 7;
      double userBudget;
      File inputFile;
      Scanner fileReader;
      Scanner scnr = new Scanner(System.in);
      String fileString;
      String userCondition;
      String[] data = new String[csvFields];

      System.out.println("\nAre you looking to buy a new or used car?\nEnter \"new\" or \"used\" below.");
      userCondition = scnr.nextLine().trim().toLowerCase();
      while(!userCondition.equals("new") && !userCondition.equals("used")){
        System.out.println("\nPlease enter \"new\" or \"used\"");
        userCondition = scnr.nextLine().trim().toLowerCase();
      }
      try{
        System.out.println("\nWhat is the most you are looking to spend? Enter a number below");
        userBudget = scnr.nextDouble();
        System.out.println("\nDisplaying results with applied filters...");
        try{
          inputFile = new File("car-database.csv");
          fileReader = new Scanner(inputFile);
          System.out.print("\n\nCar ID| Model| Manufacturer| Condition| Color| Year| Price\n----------------------------------------------------------");
          System.out.println("");
          while(fileReader.hasNextLine()){
            fileString = fileReader.nextLine();
            data = fileString.split(",");
            if(data[3].equals(userCondition) && Double.parseDouble(data[6]) <= userBudget){
              System.out.println(Arrays.toString(data));
              carMatches++;
            }
          }
          if(carMatches == 0){
            System.out.println("\nYour filters turned up no results, goodbye.");
            return 0;
          }
          System.out.println("----------------------------------------------------------\n\nEnter the Car ID for the vehicle you wish to purchase");
          carID = scnr.nextInt();
          fileReader = new Scanner(inputFile);
          fileReader.nextLine();
          while(fileReader.hasNextLine()){
            fileString = fileReader.nextLine();
            data = fileString.split(",");
            if(Double.parseDouble(data[0]) == carID){
              System.out.println("\nVehicle selected: " + data[4] + " " + data[2] + " " + data[1] + ", " + data[5]);
              return Double.parseDouble(data[6]);
            }
          }
          System.out.println("The ID you entered doesn't match a vehicle in our records");
          System.exit(0);
        }
        catch(FileNotFoundException e){
          System.out.println("Database not found.");
          System.exit(0);
        }
      }
      catch(InputMismatchException e){
        System.out.println("\nInvalid input, goodbye");
        System.exit(0);
      }
      return 0;
  }
}
