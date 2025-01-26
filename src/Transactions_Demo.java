import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions_Demo {
    static String URL = "jdbc:mysql://localhost:3306/atharva_db";
    static String USERNAME = "root";
    static String PASSWORD = "Roger365";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement withDrawStatemenet = null;
        PreparedStatement depositStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Successfully connected to Database!!!");
        } catch (Exception exception) {
            System.err.println("Failed to connect to Database: " + exception.getMessage());
            throw new RuntimeException(exception);
        }

        try {
            connection.setAutoCommit(false);
            String withDrawQuery = "UPDATE accounts SET balance = balance - ? where accountNumber = ?";
            String depositQuery = "UPDATE accounts SET balance = balance + ? where accountNumber = ?";
            withDrawStatemenet = connection.prepareStatement(withDrawQuery);
            depositStatement = connection.prepareStatement(depositQuery);

            withDrawStatemenet.setDouble(1, 500.00);
            withDrawStatemenet.setString(2, "AC1");
            withDrawStatemenet.executeUpdate();

            depositStatement.setDouble(1, 500.00);
            depositStatement.setString(2, "AC2");
            depositStatement.executeUpdate();
            System.out.println("Transaction Successful!!!");
            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
            System.out.println("Transactions have been rolled back!!!");
            System.out.println("Transaction Failed with error: " + exception.getMessage());
        }
    }
}
