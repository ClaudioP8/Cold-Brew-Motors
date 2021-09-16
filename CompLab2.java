/*
*Author: Claudio Palomares
*Course: CS1101
*Instructor: Dr. Daniel Mejia
*Description: Program implements topics covered in this course to simulate a car buying experience
*/

import java.util.*;
import java.io.*;

public class CompLab2{
  public static void main(String[] args){
    stepOne();
  }

  public static void stepOne(){
    Scanner scnr = new Scanner(System.in);
    System.out.println("Welcome to Cold Brew Motors, let's get you registered!\n\nYour username must be at least 6 characters long, enter it below or \"e\" to exit.");
    setUsername(scnr.nextLine());
    System.out.println("\nNow let's set up a password, the criteria are below.\n\n- 8 characters or more\n- 1 lowercase letter");
    System.out.println("- 1 uppercase letter\n- 1 digit\n- 1 special character (@ | ! | & | $)\n\nEnter a password below or \"e\" to exit.");
    setPassword(scnr.nextLine());
    stepFour(stepTwoThree());
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
          System.out.println("Database not found: " + e);
          System.exit(0);
        }
      }
      catch(InputMismatchException e){
        System.out.println("\nInvalid input: " + e);
        System.exit(0);
      }
      return 0;
  }

  public static void stepFour(double carPrice){
    Scanner scnr = new Scanner(System.in);
    int paymentChoice;
    int financingChoice;
    carPrice = totalValue(carPrice);
    try{
    System.out.println("How would you like to pay? 1. Pay in full | 2. See financing options");
    paymentChoice = scnr.nextInt();
    if(paymentChoice == 1){
      System.out.println("Total amount due: $" + carPrice + "\nThank you for buying your car at Cold Brew Motors");
      System.exit(0);
    }
    System.out.println("\nFinancing options available:\n1. 48 month loan | 3.5% APR\n2. 60 month loan | 4.2% APR\n3. 72 month loan | 5.2% APR\nEnter your choice below");
    financingChoice = scnr.nextInt();
    while(financingChoice != 1 && financingChoice != 2 && financingChoice != 3){
      System.out.println("Please enter a valid financing choice");
      financingChoice = scnr.nextInt();
    }
    System.out.printf("Monthly payment: $%.2f\n",  paymentCalculator(carPrice, financingChoice));
    System.out.print("Thank you for buying your car at Cold Brew Motors");
    }
    catch(Exception e){
      System.out.println("Error: " + e);
    }
  }

  /***The methods below are for use by stepOne***/
  public static void setUsername(String username){
    Scanner sUScanner = new Scanner(System.in);
    while(username.length() < 6){
      if(username.equals("e")){
        System.exit(0);
      }
      System.out.println("\nThat username is too short, try again.");
      username = sUScanner.nextLine();
    }
  }


  public static void setPassword(String password){
    Scanner scnr = new Scanner(System.in);
    boolean validPassword = false;
    String confirmPassword;

    while(validPassword != true){
      if(password.equals("e")){
        System.exit(0);
      }
      if(password.length() >= 8){
        if(containsLowerCase(password)){
          if(containsUpperCase(password)){
            if(containsDigit(password)){
              if(containsSpecial(password)){
                validPassword = true;
              }
              else{
                System.out.println("\nYou're missing a special character, try again.");
                password = scnr.nextLine();
              }
            }
            else{
              System.out.println("\nYou're missing a digit, try again.");
              password = scnr.nextLine();
            }
          }
          else{
            System.out.println("\nYou're missing an uppercase character, try again.");
            password = scnr.nextLine();
          }
        }
        else{
          System.out.println("\nYou're missing a lowercase character, try again.");
          password = scnr.nextLine();
        }
      }
      else{
        System.out.println("\nYour password is too short, try again.");
        password = scnr.nextLine();
      }
    }
    System.out.println("\nPlease enter your password one more time or \"e\" to exit.");
    confirmPassword = scnr.nextLine();
    while(!confirmPassword.equals(password)){
      if(confirmPassword.equals("e")){
        System.exit(0);
      }
      System.out.println("Your passwords do not match, try again.");
      confirmPassword = scnr.nextLine();
    }
    System.out.println("\nYou have been successfully registered!");
  }

  /***The methods below are for use by setPassword***/
  //Method implements a for loop to iterate through each character of a string and check if it is an uppercase character through ASCII value. Returns boolean for use in other methods.
  public static boolean containsUpperCase(String str){
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
        return true;
      }
    }
    return false;
  }

  //Method implements a for loop to iterate through each character of a string and check if it is a lowercase character through ASCII value. Returns boolean for use in other methods.
  public static boolean containsLowerCase(String str){
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
        return true;
      }
    }
    return false;
  }

  //Method implements a for loop to iterate through each character of a string and check if it is a number through Character.isDigit() method. Returns boolean for use in other methods.
  public static boolean containsDigit(String str){
    for(int i = 0; i < str.length(); i++){
      if(Character.isDigit(str.charAt(i))){
        return true;
      }
    }
    return false;
  }

  //Method implements a for loop to iterate through each character of a string and check if it is one of the specified special characters. Returns boolean for use in other methods.
  public static boolean containsSpecial(String str){
    if(str.contains("@") || str.contains("!") || str.contains("&") || str.contains("$")){
      return true;
    }
    return false;
  }

  /***The methods below are for use by stepFour***/
  public static double totalValue(double carPrice){
    int totalDiscount = getDiscount();
    return (carPrice - totalDiscount + ((carPrice - totalDiscount) * 0.05) + 109);
  }

  public static double paymentCalculator(double finalPrice, int financingChoice){
    if(financingChoice == 1){
      return ((finalPrice * 0.035) + finalPrice) / 48;
    }
    else if(financingChoice == 2){
      return ((finalPrice * 0.042) + finalPrice) / 60;
    }
    else if(financingChoice == 3){
      return ((finalPrice * 0.052) + finalPrice) / 72;
    }
    return 0;
  }

  //The method below is for use by totalValue
  public static int getDiscount(){
    try{
    int discount = 0;
    int response;
    Scanner scnr = new Scanner(System.in);

    System.out.println("\nAre you a student? 1. Yes | 2. No");
    response = scnr.nextInt();
    if(response == 1){
      discount += 500;
    }
    System.out.println("\nAre you military (Active, reserve, or a veteran)? 1. Yes | 2. No");
    response = scnr.nextInt();
    if(response == 1){
      discount += 500;
    }
    System.out.println("\nAre you a first responder? 1. Yes | 2. No");
    response = scnr.nextInt();
    if(response == 1){
      discount += 500;
    }
    System.out.println("\nYou qualify for $" + discount + " in discounts!");
    return discount;
    }
    catch(Exception e){
      System.out.println("Invalid input: " + e);
      System.exit(0);
    }
    return 0;
  }
}
