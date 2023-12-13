/*
* Title: Class Desription Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class is a description of a specific class that retains everything
*   except the semester.
*/

public class ClassDescription {
    // Member declarations
    private String courseCode;
    private String description;
    private int seats;

    public ClassDescription(String courseCode, String description, int seats) {
        this.courseCode = courseCode;
        this.description = description;
        this.seats = seats;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }

    public int getSeats() {
        return seats;
    }
}