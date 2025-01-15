import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Insert {
    static String url = "jdbc:mysql://localhost:3306/atharva_db";
    static String username = "root";
    static String password = "Roger365";
    static Connection connection = null;
    static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to connect to database: " + exception.getMessage());
        }

        try {
            statement = connection.createStatement();
            LocalDate date = LocalDate.now();
            int affectedRows = statement.executeUpdate("INSERT INTO atharva_db.Employee VALUES(6, 'Atharva', 'Dhamale', 'atharva.dhamale@privacera.com', '" + date + "', 750000)");
            System.out.println("Date inserted successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to insert data: " + exception.getMessage());
        }
        statement.close();
        connection.close();
    }
}
