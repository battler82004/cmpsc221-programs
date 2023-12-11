/*
* Title: Customer Class
* Author: James Taddei
* Date: 2023-09-17
* Dscription:
*   This class takes a first name, last name, and SSN and creates a customer
*   which holds and outputs the customer's loan(s).
*/

package programmingassignment3;

import java.util.ArrayList;

// Class for each customer
public class Customer {
    // Class variable declarations
    private final String firstName;
    private final String lastName;
    private final String SSN; // social security number with dashes as String
    private final ArrayList<LoanAccount> loanAccounts = new ArrayList<>();
        // Stores each of the user's loans
    
    public Customer(String firstName, String lastName, String SSN) {
        // Constructor
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
    }
    
    public String getFirstName() { return firstName; }
    
    public String getLastName() { return lastName; }
    
    public String getSSN() { return SSN; }
    
    public void addLoanAccount(LoanAccount account) {
        loanAccounts.add(account);
    }
    
    public void printMonthlyReport() {
        // Prints the customer's name, SSN, and their loans.
        
        System.out.printf("Account Report for Customer: %s %s with SSN %s%n",
                firstName, lastName, SSN);
        for (LoanAccount account : loanAccounts) {
            System.out.println(account);
        }
    }
}