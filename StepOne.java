/*
*Claudio Palomares
*Comprehensive Lab Two: Step One - Sign up
*/

import java.util.Scanner;

public class StepOne{

  public static void main(String[] args){
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("Let's get you registered!\n\nYour username must be at least 6 characters long, enter it below or \"e\" to exit.");
    setUsername(inputScanner.nextLine());
    System.out.println("\nNow let's set up a password, the criteria are below.\n\n- 8 characters or more\n- 1 lowercase letter");
    System.out.println("- 1 uppercase letter\n- 1 digit\n- 1 special character (@ | ! | & | $)\n\nEnter a password below or \"e\" to exit.");
    setPassword(inputScanner.nextLine());


  }


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
    Scanner sPScanner = new Scanner(System.in);
    boolean validPassword = false;
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
                System.out.println("You're missing a special character, try again.");
                password = sPScanner.nextLine();
              }
            }
            else{
              System.out.println("You're missing a digit, try again.");
              password = sPScanner.nextLine();
            }
          }
          else{
            System.out.println("You're missing an uppercase character, try again.");
            password = sPScanner.nextLine();
          }
        }
        else{
          System.out.println("You're missing a lowercase character, try again.");
          password = sPScanner.nextLine();
        }
      }
      else{
        System.out.println("Your password is too short, try again.");
        password = sPScanner.nextLine();
      }
    }
    System.out.println("\nPlease enter your password one more time.");
    String confirmPassword = sPScanner.nextLine();
    while(!confirmPassword.equals(password)){
      System.out.println("Your passwords do not match, try again.");
      confirmPassword = sPScanner.nextLine();
    }
    System.out.println("\nYou have been successfully registered.");
  }

  //***The methods below are for use by setPassword***
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
  //The Character.isDigit() method was obtained outside of class, reference: https://www.geeksforgeeks.org/character-isdigit-method-in-java-with-examples
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
}
