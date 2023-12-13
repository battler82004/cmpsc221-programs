/*
* Title: Multi Table Queries Class
* Author: James Taddei
* Date: 2023-12-12
* Dscription:
*   This class handles queries relating to multiple tables.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MultiTableQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static ResultSet resultSet;
    
    // Returns an array list of a description for every class during a given
    // semester.
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptions = 
                new ArrayList<ClassDescription>();
        try
        {
            getAllClassDescriptions = connection.prepareStatement("select "
                    + "app.class.courseCode, description, seats from app.class, "
                    + "app.course where semester = ? and app.class.courseCode = "
                    + "app.course.courseCode order by app.class.courseCode");
            getAllClassDescriptions.setString(1, semester);
            resultSet = getAllClassDescriptions.executeQuery();
            
            while(resultSet.next())
            {
                classDescriptions.add(new ClassDescription(resultSet.getString(1), 
                        resultSet.getString(2), 
                        Integer.parseInt(resultSet.getString(3))));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classDescriptions;   
    }
    
    // Returns an arry list of every student scheduled for a specific class.
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(
            String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> scheduledStudents = new ArrayList<StudentEntry>();
        try
        {
            getScheduledStudentsByClass = connection.prepareStatement("select "
                    + "studentid from app.schedule where semester = ? and "
                    + "coursecode = ? and status = 'S'");
            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2, courseCode);
            resultSet = getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                scheduledStudents.add(StudentQueries.getStudent(resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
    
    // Returns an array list of every student waitlisted for a specific class.
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(
            String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlistedStudents = new ArrayList<StudentEntry>();
        try
        {
            getWaitlistedStudentsByClass = connection.prepareStatement("select "
                    + "studentid from app.schedule where semester = ? and "
                    + "coursecode = ? and status = 'W' order by timestamp asc");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                waitlistedStudents.add(StudentQueries.getStudent(resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistedStudents;
    }
}