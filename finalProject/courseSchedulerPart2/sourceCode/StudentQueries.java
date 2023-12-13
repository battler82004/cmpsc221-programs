/*
* Title: Student Queries Class
* Author: James Taddei
* Date: 2023-12-12
* Dscription:
*   This class handles queries relating to the Student table.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    // Adds a StudentEntry object to the Student table.
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student "
                    + "(studentid, firstname, lastname) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Returns an array list of all of the students in the student table.
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, "
                    + "firstname, lastname from app.student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1), 
                        resultSet.getString(2), resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;   
    }
    
    // Returns the student entry for a given studentID.
    public static StudentEntry getStudent(String studentID) {
        connection = DBConnection.getConnection();
        StudentEntry student = new StudentEntry("", "", "");
        try
        {
            getStudent = connection.prepareStatement("select studentid, "
                    + "firstname, lastname from app.student where studentid = ?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next())
            {
                student = new StudentEntry(resultSet.getString(1), 
                        resultSet.getString(2), resultSet.getString(3));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    // Drops a student entry from the student table.
    public static void dropStudent(String studentID) {
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student "
                    + "where studentid = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}