/*
* Title: Unsecured Loan Class
* Author: James Taddei
* Date: 2023-09-17
* Dscription:
*   This class takes a principal loan amount, annual interest rate, and number
*   of months and determines the monthly payments or outputs all of the data.
*/

package programmingassignment3;

// Class for each unsecured loan
public class UnsecuredLoan extends LoanAccount {
    
    public UnsecuredLoan(double principal, double annualInterestRate,
            int months) {
        // Constructor
        
        super(principal, annualInterestRate, months);
    }
    
    @Override
    public String toString() {
        // Returns a formmated string of the loan's principal, annual interest
        // rate, number of months, and monthly payment.
        
        return String.format("Unsecured Loan with:%n%s", super.toString());
    }
}