/*
* Title: Car Loan Class
* Author: James Taddei
* Date: 2023-09-10
* Dscription:
*   This class takes a principal loan amount, annual interest rate, number of 
*   months, and vehicle VIN and determines the monthly payments or outputs all
*   of the data.
*/

package programmingassignment2;

// Class for each car loans
public class CarLoan extends LoanAccount {
    // Class variable declaration
    private final String vehicleVIN; // VIN number
    
    public CarLoan(double principal, double annualInterestRate, int months,
            String vehicleVIN) {
        // Constructor
        
        super(principal, annualInterestRate, months);
        this.vehicleVIN = vehicleVIN;
    }
    
    @Override
    public String toString() {
        // Returns a formmated string of the loan's principal, annual interest
        // rate, number of months, monthly payment, and the vehicle's VIN.
        
        return String.format("Car Loan with:%n%sVehicle VIN: %s%n",
                super.toString(), vehicleVIN);
    }
}