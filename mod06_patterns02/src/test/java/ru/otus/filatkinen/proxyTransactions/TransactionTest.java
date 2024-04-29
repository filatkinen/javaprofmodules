package ru.otus.filatkinen.proxyTransactions;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {

    @Test
    void runSimpleTransactionTest() throws SQLException {

        String connectionUrl = "jdbc:h2:mem:test";
        DBStorage dbStorage = new DBProxy(connectionUrl);
        dbStorage.connect();

        List<String> initDB = new ArrayList<>();
        initDB.add("Create table students (ID int primary key, name varchar(50))");
        initDB.add("Insert into students (ID, name) values (1, 'Nam Ha Minh')");
        dbStorage.runTransaction(initDB);

        List<String> queryDB = new ArrayList<>();
        queryDB.add("Select * from students");
        dbStorage.runTransaction(queryDB);

        dbStorage.disconnect();

    }
}
