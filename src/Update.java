import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update {
    static String url = "jdbc:mysql://localhost:3306/atharva_db";
    static String username = "root";
    static String password = "Roger365";
    static Connection connection;
    static Statement statement;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to connect to Database: " + exception.getMessage());
        }

        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Employee SET salary=800000 WHERE EmployeeID=6");
            System.out.println("Table updated successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to update table: " + exception.getMessage());
        }
    }
}
