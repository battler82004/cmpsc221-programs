/*
* Title: Programming Assignment 1 - Loan Account Class
* Author: James Taddei
* Date: 2023-08-30
* Dscription:
*   This program determines the monthly payments for 2 different loans after 3,
*   5, and 6 years assuming interest rates of 1% and 5%.
*/

package programmingassignment1;

// Monthly loan payment determiner program
public class ProgrammingAssignment1 {
    // Constant declarations
    static final double LOAN_AMOUNT_1 = 5_000;
    static final double LOAN_AMOUNT_2 = 31_000;
    static final int[] ANNUAL_INTEREST_RATES = {1, 5}; // in %
    static final int[] MONTHS_OF_INTEREST = {36, 60, 72};
    static final int NUM_OF_MONTHS = 3;
    
    public static void main(String[] args) {
        // Determines the monthly interest payments at years 3, 5, and 6 given 2
        // principle loan amounts and annual interest rates of 1% and 5%.
        
        // Variable declarations
        LoanAccount loan1 = new LoanAccount(LOAN_AMOUNT_1);
        LoanAccount loan2 = new LoanAccount(LOAN_AMOUNT_2);
        
        // Prints the monthly payments for the loan at 2 different interest rates
        for (int interestRate : ANNUAL_INTEREST_RATES) {
            LoanAccount.setAnnualInterestRate(interestRate / 100.0 /* convert rate from % */);
            
            // Outputs the header before the monthly payments are printed
            System.out.printf("Monthly payments for loan1 of $%.2f and loan2 of $%.2f for %d, %d, and %d year loans at %d%% interest.%n",
                    LOAN_AMOUNT_1, LOAN_AMOUNT_2, MONTHS_OF_INTEREST[0] / 12, MONTHS_OF_INTEREST[1] / 12, MONTHS_OF_INTEREST[2] / 12, interestRate);
            System.out.printf("Loan    %d years %d years %d years%n",
                    MONTHS_OF_INTEREST[0] / 12, MONTHS_OF_INTEREST[1] / 12, MONTHS_OF_INTEREST[2] / 12);
            
            // Determines and outputs the monthly payments for loan1 and loan2
            outputMonthlyPayments(loan1, 1);
            outputMonthlyPayments(loan2, 2);
            System.out.println();
        }
    }
    
    private static void outputMonthlyPayments(LoanAccount loan, int num) {
        double[] monthlyPayments = new double[NUM_OF_MONTHS]; // variable declaration
        
        for (int i = 0; i < NUM_OF_MONTHS; i++) {
            // Calculates and stores the 3 monthly payment values
            monthlyPayments[i] = loan.calculateMonthlyPayment(MONTHS_OF_INTEREST[i]);
        }
        // Outputs the monthly paymnt values
        System.out.printf("Loan%d   %-7.2f %-7.2f %-7.2f%n", num, monthlyPayments[0], monthlyPayments[1], monthlyPayments[2]);
    }
    
}
