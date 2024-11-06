package com.eventfy.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerJdbcTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlserver://DESKTOP-HVLJES2\\MSSQLSERVER01:62116;database=EventFy;encrypt=false";

        String user = "sa";
        String password = "328232";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            System.out.println("Conexão JDBC com SQL Server estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar com o SQL Server via JDBC.");
            e.printStackTrace();
        }
    }
}
