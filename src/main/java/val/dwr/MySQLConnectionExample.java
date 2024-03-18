package val.dwr;

import java.sql.*;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // Connection parameters
        String username = "debayan";
        String password = "root";
        String databaseName = "studentdb";

        // JDBC URL for MySQL
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        // SQL query to execute
        String query = "SELECT * FROM student";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create statement
            Statement statement = connection.createStatement();

            // Execute query
            ResultSet resultSet = statement.executeQuery(query);

            // Process result set
            while (resultSet.next()) {
                // Assuming student table has columns "name" and "age"
                int id = resultSet.getInt("id");
                String name = resultSet.getString("firstname");
                String email = resultSet.getString("email");

                // Print results
                System.out.println("ID: " + id +", Name: " + name + ", Email: " + email);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
