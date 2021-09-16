/*
*Claudio Palomares
*Comprehensive Lab Two: Step Four - Checkout
*/

import java.util.Scanner;

public class StepFour{
  public static void main(String[] args){
    Scanner scnr = new Scanner(System.in);
    double carPrice = 65000.0;
    int paymentChoice;
    int financingChoice;
    carPrice = totalValue(carPrice);
    System.out.println("How would you like to pay? 1. Pay in full 2. See financing options");
    paymentChoice = scnr.nextInt();
    if(paymentChoice == 1){
      System.out.println("Total amount due: $" + carPrice + "\nThank you for buying your car at Cold Brew Motors");
      System.exit(0);
    }
    System.out.println("Financing options available:\n1. 48 month loan, 3.5% APR\n2. 60 month loan, 4.2% APR\n3. 72 month loan, 5.2APR\nEnter a number below");
    financingChoice = scnr.nextInt();
    while(financingChoice != 1 && financingChoice != 2 && financingChoice != 3){
      System.out.println("Please enter a valid financing choice");
      financingChoice = scnr.nextInt();
    }
    System.out.printf("Monthly payment: $%.2f\n",  paymentCalculator(carPrice, financingChoice));
    System.out.print("Thank you for buying your car at Cold Brew Motors");
  }

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
}
