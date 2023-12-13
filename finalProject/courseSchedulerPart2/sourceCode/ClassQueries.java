/*
* Title: Class Queries Class
* Author: James Taddei
* Date: 2023-12-12
* Dscription:
*   This class handles queries relating to the Class table.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClassQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement dropClass;
    private static ResultSet resultSet;
    
    // Adds a ClassEntry object to the Class table.
    public static void addClass(ClassEntry classEntry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class "
                    + "(semester, coursecode, seats) values (?, ?, ?)");
            addClass.setString(1, classEntry.getSemester());
            addClass.setString(2, classEntry.getCourseCode());
            addClass.setString(3, Integer.toString(classEntry.getSeats()));
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Returns an array list of all of the course codes with classes during
    // a given semester.
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode "
                    + "from app.class where semester = ?");
            getAllCourseCodes.setString(1, semester);
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
    
    // Returns the total number of seats for a specific class. Returns -1 if
    // there is an error in the process.
    public static int getClassSeats(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        int seats = -1;
        try
        {
            getClassSeats = connection.prepareStatement("select seats from "
                    + "app.class where semester = ? and coursecode = ?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
            
            while (resultSet.next()) {
                seats = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
    }
    
    // Drops a given class from the class table.
    public static void dropClass(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        try
        {
            dropClass = connection.prepareStatement("delete from app.class "
                    + "where semester = ? and coursecode = ?");
            dropClass.setString(1, semester);
            dropClass.setString(2, courseCode);
            dropClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}