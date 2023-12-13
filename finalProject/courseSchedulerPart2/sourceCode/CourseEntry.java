/*
* Title: Course Entry Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class is a course entry object which contains a code and description
*   for a course.
*/

public class CourseEntry {
    // Member declarations
    private String courseCode;
    private String description;

    public CourseEntry(String courseCode, String description) {
        this.courseCode = courseCode;
        this.description = description;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }
}