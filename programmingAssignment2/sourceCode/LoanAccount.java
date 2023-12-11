/*
* Title: Loan Account Class
* Author: James Taddei
* Date: 2023-09-10
* Dscription:
*   This class takes a principal loan amount, annual interest rate, and number
*   of months and determines the monthly payments or outputs all of the data.
*/

package programmingassignment2;

// Superclass for loans
public class LoanAccount {
    // Class variable declarations
    private final double principal; // original amount of loan
    private final double annualInterestRate; // decimal interest rate
    private final int months; // length of the loan
    
    public LoanAccount(double principal, double annualInterestRate, int months) {
        // Constructor
        
        this.principal = principal;
        this.annualInterestRate = annualInterestRate / 100; // percent to decimal
        this.months = months;
    }
    
    public double getPrincipal() { return principal; }
    
    public double getAnnualInterestRate() { return annualInterestRate; }
    
    public int getMonths() { return months; }
    
    public double calculateMonthlyPayment() {
        // Returns the monthly payment required for the loan.

        // Calculates the monthly payment value
        double monthlyInterest = annualInterestRate / 12;
        double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1
                + monthlyInterest, -months)));
        
        return monthlyPayment;
    }
    
    @Override
    public String toString() {
        // Returns a formmated string of the loan's principal, annual interest
        // rate, number of months, and monthly payment.
        
        return String.format("Principal: $%.2f%nAnnual Interest Rate: %.2f%%%n"
                + "Term of Loan in Months: %d%nMonthly Payment: $%.2f%n",
                principal, annualInterestRate * 100, months,
                calculateMonthlyPayment());
    }
}