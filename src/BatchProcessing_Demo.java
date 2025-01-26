import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchProcessing_Demo {
    static String url = "jdbc:mysql://localhost:3306/atharva_db";
    static String username = "root";
    static String password = "Roger365";
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Successfully connected to Database!!!");
        } catch (SQLException exception) {
            System.err.println("Failed to connect to database: " + exception.getMessage());
            throw new RuntimeException(exception);
        }

        try {
            String insertQuery = "INSERT INTO accounts VALUES(?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            while (true) {
                sc.nextLine();
                System.out.print("Account Number: ");
                String accountNumber = sc.nextLine();
                System.out.print("Balance: ");
                double balance = sc.nextDouble();

                preparedStatement.setString(1, accountNumber);
                preparedStatement.setDouble(2, balance);
                preparedStatement.addBatch();

                System.out.println("Add More Entries: Y/N");
                String c = sc.next();
                if (c.equalsIgnoreCase("N")) {
                    break;
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch Executed Successfully!!!");
        } catch (Exception exception) {
            System.err.println("Failed to Executed Batch: " + exception.getMessage());
            connection.rollback();
            throw new RuntimeException(exception);
        }
    }
}
