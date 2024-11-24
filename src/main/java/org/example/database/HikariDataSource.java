package org.example.database;

import com.zaxxer.hikari.HikariConfig;

import javax.sql.DataSource;

public class HikariDataSource {

    private static com.zaxxer.hikari.HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/wallet_hw11");
            config.setUsername("postgres");
            config.setPassword("postgres");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(600000);
            config.setConnectionTimeout(30000);

            dataSource = new com.zaxxer.hikari.HikariDataSource(config);
        }catch (Exception e) {
            throw new RuntimeException("Failed to initialize Hikari DataSource", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
