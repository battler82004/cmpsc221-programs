/*
* Title: Semester Queries Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class handles queries relating to the Semester table.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv
 */
public class SemesterQueries {
    // Member declarations
    private static Connection connection;
    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    
    // Adds a semester (string) to the Semester table.
    public static void addSemester(String name)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSemester = connection.prepareStatement("insert into app.semester (semester) values (?)");
            addSemester.setString(1, name);
            addSemester.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    // Returns an array list of all of the semesters.
    public static ArrayList<String> getSemesterList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        try
        {
            getSemesterList = connection.prepareStatement("select semester from app.semester order by semester");
            resultSet = getSemesterList.executeQuery();
            
            while(resultSet.next())
            {
                semester.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return semester;
        
    }
    
    
}
