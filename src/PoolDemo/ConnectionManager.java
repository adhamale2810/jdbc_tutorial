package PoolDemo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private HikariConfig hikariConfig = new HikariConfig();
    private HikariDataSource hikariDataSource;

    public ConnectionManager(String url, String username, String password) {
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(20000);
        hikariConfig.setMinimumIdle(2);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to connect to Database!!!");
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }
}
