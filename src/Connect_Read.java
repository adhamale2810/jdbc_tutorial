import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect_Read {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/atharva_db";
        String username = "root";
        String password = "Roger365";
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception exception) {
            System.err.println(exception.getMessage() + ": " + exception.getStackTrace());
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database successfully!!!");
            Statement statement = connection.createStatement();
            System.out.println("Executing Query!!!");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM atharva_db.Employee");
            System.out.println("----------Employee Data----------");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("ID: " + id);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                System.out.println("Name: " + firstName + " " + lastName);
                String email = resultSet.getString(4);
                System.out.println("Email: " + email);
                Date date = resultSet.getDate(5);
                System.out.println("Hiring Date: " + date.toString());
                double salary = resultSet.getDouble(6);
                System.out.println("Salary: Rs." + salary);
                System.out.println("---------------");
            }
            resultSet.close();
            statement.close();
        } catch (Exception exception) {
            System.err.println(exception.getMessage() + ": " + exception.getStackTrace());
        } finally {
            connection.close();
        }
    }
}
