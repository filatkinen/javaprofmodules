package ru.otus.filatkinen.proxyTransactions;

import java.sql.SQLException;
import java.util.List;

public class DBProxy implements DBStorage {
    private final DBStorage dbStorage;

    public DBProxy(String connectionString) {
        dbStorage =new DBH2(connectionString);
    }

    @Override
    public void connect() throws SQLException {
        dbStorage.connect();
    }

    @Override
    public void disconnect() throws SQLException {
        dbStorage.disconnect();
    }

    @Override
    public void execute(List<String> commands) throws SQLException {
        dbStorage.execute(commands);
    }

    @Override
    public void startTransaction() throws SQLException {
        dbStorage.startTransaction();
    }

    @Override
    public void commitTransaction() throws SQLException {
        dbStorage.commitTransaction();
    }

    @Override
    public void rollbackTransaction() throws SQLException {
        dbStorage.rollbackTransaction();
    }
}
