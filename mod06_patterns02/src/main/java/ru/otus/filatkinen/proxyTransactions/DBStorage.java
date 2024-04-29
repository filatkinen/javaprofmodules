package ru.otus.filatkinen.proxyTransactions;

import java.sql.SQLException;
import java.util.List;

public interface DBStorage {
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    void runTransaction(List<String> commands) throws SQLException;
}
