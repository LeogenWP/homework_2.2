package org.leogenwp.utils;

import liquibase.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private  String url = "jdbc:mysql://localhost:3306/writer";
    private  String username = "root";
    private  String password = "admin";
    private static ConnectDB instance;
    private Connection connection;

    private ConnectDB()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/writer","root","admin");
        }

        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

        catch(ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public static ConnectDB getInstance() throws SQLException
    {
        if (instance == null) {
            instance = new ConnectDB();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectDB();
        }

        return instance;
    }
}

