/*
* Title: Address Class
* Author: James Taddei
* Date: 2023-09-17
* Dscription:
*   This class stores and returns a street, city, state, and zipcode as part
*   of a complete address.
*/

package programmingassignment3;

// Class for each address
public class Address {
    // Class variable declarations
    private final String street; // house number and street name
    private final String city;
    private final String state;
    private final String zipcode;
    
    public Address(String street, String city, String state, String zipcode) {
        // Constructor
        
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    
    public String getStreet() { return street; }
    
    public String getCity() { return city; }
    
    public String getState() { return state; }
    
    public String getZipcode() { return zipcode; }
    
    @Override
    public String toString() {
        // Returns a formmated string of the stored address.
        
        return String.format("    %s%n    %s, %s %s", street, city, state, zipcode);
    }
}