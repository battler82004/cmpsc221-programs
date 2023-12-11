/*
* Title: Programming Assignment 2 - Loan Account Hierarchy
* Author: James Taddei
* Date: 2023-09-10
* Dscription:
*   This program creates and outputs the relevant data of a car loan, primary
*   mortgage loan, and unsecured loan.
*/

package programmingassignment2;

public class ProgrammingAssignment2 {

    public static void main(String[] args) {
        // Creates and outputs the relvenat data for 3 loans.
        
        // Create three different loan objects, one of each type.
        CarLoan carLoan = new CarLoan(25000.00, 4.25, 72, "IRQ3458977");
        
        Address propertyAddress = new Address("321 Main Street", "State College",
                "PA", "16801");
        PrimaryMortgage propertyLoan = new PrimaryMortgage(250000.00, 3.1, 360,
                35.12, propertyAddress);
        
        UnsecuredLoan unsecuredLoan = new UnsecuredLoan(5000.00, 10.75, 48);
        
        //Print out the load information for each loan using the toString() method.
        System.out.format("%n%s%n%s%n%s%n", carLoan, propertyLoan, unsecuredLoan);
    }
}
