/*
* Title: DB Connection Class
* Author: James Taddei
* Date: 2023-11-17
* Dscription:
*   This class facilates a connection between the program and DB.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Member variables
    private static Connection connection;
    private static final String user = "java";
    private static final String password = "java";
    private static final String database = "jdbc:derby://localhost:1527/CourseSchedulerDBJWT5564";

    // Connects the program to the specified DB.
    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(database, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);

            }
        }
        return connection;
    }
}
