package PoolDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolDemo {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/atharva_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Roger365";

    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager(URL, USERNAME, PASSWORD);
        connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from employee");
            while (resultSet.next()) {
                System.out.println("-------------------------");
                System.out.println("ID: " + resultSet.getString("EmployeeID"));
                System.out.println("Name: " + resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Salary: " + resultSet.getString("Salary"));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
