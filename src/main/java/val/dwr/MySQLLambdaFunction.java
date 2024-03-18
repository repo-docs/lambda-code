package val.dwr;

import java.sql.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MySQLLambdaFunction implements RequestHandler<Object, String> {

    // Connection parameters
    static final String username = "admin";
    static final String password = "Hello!2949";
    static final String databaseName = "studentdb";
    static final String url = "jdbc:mysql://database-simplified.cpoi4cs8swjs.us-east-1.rds.amazonaws.com:3306/" + databaseName;

    // SQL query to execute
    static final String query = "SELECT * FROM student";

    @Override
    public String handleRequest(Object input, Context context) {
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
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                // Assuming student table has columns "name" and "age"
                String name = resultSet.getString(0);
                int age = resultSet.getInt(1);

                // Append results
                result.append("Name: ").append(name).append(", Age: ").append(age).append("\n");
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Log the result
            System.out.println(result.toString());
            return result.toString();
        } catch (Exception e) {
            // Log and return error message
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
