package ru.otus.filatkinen.proxyTransactions;

import java.sql.SQLException;
import java.util.List;

public interface DBStorage {
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    void execute(List<String> commands) throws SQLException;
    void startTransaction() throws SQLException;
    void commitTransaction() throws SQLException;
    void rollbackTransaction() throws SQLException;
}
