/*
* Title: Primary Mortgage Class
* Author: James Taddei
* Date: 2023-09-10
* Dscription:
*   This class takes a principal loan amount, annual interest rate, number of 
*   months, PMI monthly amount, and address and determines the monthly payments
*   or outputs all of the data.
*/

package programmingassignment2;

// Class for each primary mortgage
public class PrimaryMortgage extends LoanAccount {
    // Class variable declarations
    private final double PMIMonthlyAmount; // $ amount
    private final Address address; // property address
    
    public PrimaryMortgage(double principal, double annualInterestRate,
            int months, double PMIMonthlyAmount, Address address) {
        // Constructor
        
        super(principal, annualInterestRate, months);
        this.PMIMonthlyAmount = PMIMonthlyAmount;
        this.address = address;
    }
    
    @Override
    public String toString() {
        // Returns a formmated string of the loan's principal, annual interest
        // rate, number of months, monthly payment, PMI monthly amount, and
        // property address.
        
        return String.format("Primary Mortgage Loan with:%n%sPMI Monthly Amount:"
                + " $%.2f%nProperty Address:%n%s%n", super.toString(),
                PMIMonthlyAmount, address);
    }
}