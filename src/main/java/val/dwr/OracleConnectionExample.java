package val.dwr;

import java.sql.*;

public class OracleConnectionExample {
    public static void main(String[] args) {
        // Connection parameters
        String username = "E190";
        String password = "E190";
        String sid = "prim";

        // JDBC URL for Oracle
        String url = "jdbc:oracle:thin:@localhost:1521:" + sid;

        // SQL query to execute
        String query = "SELECT * FROM student";

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create statement
            Statement statement = connection.createStatement();

            // Execute query
            ResultSet resultSet = statement.executeQuery(query);

            // Process result set
            while (resultSet.next()) {
                // Assuming student table has columns "student_id" and "student_name"
                int studentId = resultSet.getInt("age");
                String studentName = resultSet.getString("name");

                // Print results
                System.out.println("Student AGE: " + studentId + ", Student Name: " + studentName);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading Oracle JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
