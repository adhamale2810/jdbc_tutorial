import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementDemo {
    static String URL = "jdbc:mysql://localhost:3306/atharva_db";
    static String USERNAME = "root";
    static String PASSWORD = "Roger365";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Successfully Connected to Database!!!");
        } catch (Exception exception) {
            System.err.println("Failed to connect to Database: " + exception.getMessage());
        }
        try {
            String query = "SELECT * FROM atharva_db.Employee WHERE salary >= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 80000);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("-------------------------");
                System.out.println("ID: " + resultSet.getString("EmployeeID"));
                System.out.println("Name: " + resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Salary: " + resultSet.getString("Salary"));
            }
            statement.close();
        } catch (Exception exception) {
            System.out.println("Failed to execute Query: " + exception.getMessage());
        }
        connection.close();
    }
}
