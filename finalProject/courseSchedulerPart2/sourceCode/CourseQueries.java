/*
* Title: Course Queries Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class handles queries relating to the Course table.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CourseQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;
    
    // Adds a course object to the Course table.
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (coursecode, description) values (?, ?)");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Returns an array list of all of the course codes.
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.course");
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;   
    }
}