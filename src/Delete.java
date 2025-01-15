import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    static String url = "jdbc:mysql://localhost:3306/atharva_db";
    static String username = "root";
    static String password = "Roger365";
    static Connection connection;
    static Statement statement;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connected to Database successfully!!!");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception exception) {
            System.err.println("Failed to connect to Database: " + exception.getMessage());
        }

        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Employee WHERE EmployeeID=5");
            System.out.println("Row Deleted successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to delete Row: " + exception.getMessage());
        }
        statement.close();
        connection.close();
    }
}
