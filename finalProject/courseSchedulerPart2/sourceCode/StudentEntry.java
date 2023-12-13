/*
* Title: Student Entry Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class is a student object which stores an ID and first and last name.
*/

public class StudentEntry {
    // Member declarations
    private String studentID;
    private String firstName;
    private String lastName;
    
    public StudentEntry(String studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String toString() {
        return String.format("%s, %s %s", lastName, firstName, studentID);
    }
}