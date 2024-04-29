package ru.otus.filatkinen.proxyTransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBH2 implements DBStorage{
    private String connectionString;
    Connection connection;

    public DBH2(String connectionString) {
        this.connectionString = connectionString;

    }



    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(connectionString);
        System.out.println("Connected to H2 in-memory database.");

    }

    @Override
    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Disconnected from H2 in-memory database.");
    }

    @Override
    public void runTransaction(List<String> commands) throws SQLException {
        System.out.println("Running transaction..." + commands.toString());
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for (String s: commands){
            statement.execute(s);
        }
        connection.commit();
        System.out.println("End transaction");
    }
}

