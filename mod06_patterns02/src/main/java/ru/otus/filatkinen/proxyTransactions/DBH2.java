package ru.otus.filatkinen.proxyTransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBH2 implements DBStorage{
    private String connectionString;
    Connection connection;
    Statement statement;


    public DBH2(String connectionString) {
        this.connectionString = connectionString;

    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(connectionString);
        System.out.println("Connected to H2 in-memory database.");
        statement = connection.createStatement();
    }

    @Override
    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Disconnected from H2 in-memory database.");
    }

    @Override
    public void execute(List<String> commands) throws SQLException {
        System.out.println("Executing commands..." + commands.toString());
        for (String s: commands){
            statement.execute(s);
        }
    }

    @Override
    public void startTransaction() throws SQLException {
        System.out.println("Start transaction");
        connection.setAutoCommit(false);
    }

    @Override
    public void commitTransaction() throws SQLException {
        System.out.println("Commit transaction");
        connection.commit();
        statement = connection.createStatement();

    }

    @Override
    public void rollbackTransaction() throws SQLException {
        System.out.println("Rollback transaction");
        connection.rollback();
        statement = connection.createStatement();
    }
}

