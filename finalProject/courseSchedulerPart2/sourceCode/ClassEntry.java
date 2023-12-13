/*
* Title: Class Entry Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class is a class object which is a course run for a specific semester
*   with a certain number of seats.
*/

public class ClassEntry {
    // Member declarations
    private String semester;
    private String courseCode;
    private int seats;

    public ClassEntry(String semester, String courseCode, int seats) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.seats = seats;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getSeats() {
        return seats;
    }
}