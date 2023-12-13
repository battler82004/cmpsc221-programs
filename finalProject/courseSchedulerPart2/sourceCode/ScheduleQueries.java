/*
* Title: Schedule Queries Class
* Author: James Taddei
* Date: 2023-12-12
* Dscription:
*   This class handles queries relating to the Schedule table.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ScheduleQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement isStudentWaitlisted;
    private static ResultSet resultSet;
    
    // Adds a ScheduleEntry object to the Schedule table.
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into "
                    + "app.schedule (semester, coursecode, studentid, status, "
                    + "timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setString(5, entry.getTimestamp().toString());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Returns an array list of every class a given student is scheduled to take
    // during the inputted semester.
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester,
            String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select "
                    + "coursecode, status, timestamp from app.schedule where "
                    + "semester = ? and studentid =?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                schedules.add(new ScheduleEntry(semester, 
                        resultSet.getString(1), studentID, 
                        resultSet.getString(2), resultSet.getTimestamp(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedules;
    }
    
    // Returns the number of students already scheduled for the inputted course.
    public static int getScheduledStudentCount(String semester, String 
            courseCode) {
        connection = DBConnection.getConnection();
        int scheduledStudentCount = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select "
                    + "count(studentID) from app.schedule where semester = ? "
                    + "and courseCode = ?");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while (resultSet.next()) {
                scheduledStudentCount = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudentCount;   
    }
    
    // Returns an array list of the entry for schedule entry for every
    // waitlisted student.
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(
            String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedStudents = 
                new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedStudentsByClass = connection.prepareStatement("select "
                    + "studentid, status, timestamp from app.schedule where "
                    + "semester = ? and coursecode = ? and status = 'W' order "
                    + "by timestamp asc");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                waitlistedStudents.add(new ScheduleEntry(semester, courseCode, 
                        resultSet.getString(1), resultSet.getString(2), 
                        resultSet.getTimestamp(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistedStudents;
    }
    
    // Drops every schedule entry for the given student.
    public static void dropStudentScheduleByCourse(String semester, 
            String studentID, String courseCode) {
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete "
                    + "from app.schedule where semester = ? and studentid = ? "
                    + "and coursecode = ?");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Drops every schedule entry for a specific class
    public static void dropScheduleByCourse(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from "
                    + "app.schedule where semester = ? and coursecode = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Updates a specific schedule entry from waitlisted to scheduled.
    public static void updateScheduleEntry(ScheduleEntry entry) {
        connection = DBConnection.getConnection();
        try
        {
            updateScheduleEntry = connection.prepareStatement("update "
                    + "app.schedule set status = 'S' where semester = ? and "
                    + "coursecode = ? and studentid = ?");
            updateScheduleEntry.setString(1, entry.getSemester());
            updateScheduleEntry.setString(2, entry.getCourseCode());
            updateScheduleEntry.setString(3, entry.getStudentID());
            updateScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    // Returns a boolean on whether or not the given student is waitlisted
    // for the given course.
    public static boolean isStudentWaitlisted(String semester, String studentID,
            String courseCode) {
        connection = DBConnection.getConnection();
        boolean studentIsWaitlisted = false;
        try
        {
            isStudentWaitlisted = connection.prepareStatement("select status "
                    + "from app.schedule where semester = ? and studentid = ? "
                    + "and coursecode = ?");
            isStudentWaitlisted.setString(1, semester);
            isStudentWaitlisted.setString(2, studentID);
            isStudentWaitlisted.setString(3, courseCode);
            resultSet = isStudentWaitlisted.executeQuery();
            
            while(resultSet.next())
            {
                if (resultSet.getString(1) == "W") {
                    studentIsWaitlisted = true;
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentIsWaitlisted;
    }
}