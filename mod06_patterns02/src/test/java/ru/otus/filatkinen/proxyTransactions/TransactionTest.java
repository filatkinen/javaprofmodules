package ru.otus.filatkinen.proxyTransactions;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TransactionTest {

    @Test
    void runSimpleTransactionTest()  {
        String connectionUrl = "jdbc:h2:mem:test";
        DBStorage dbStorage = new DBProxy(connectionUrl);

        try {
            dbStorage.connect();

            dbStorage.startTransaction();
            List<String> initDB = new ArrayList<>();
            initDB.add("Create table students (ID int primary key, name varchar(50))");
            initDB.add("Insert into students (ID, name) values (1, 'Nam Ha Minh')");
            dbStorage.execute(initDB);
            dbStorage.commitTransaction();

            dbStorage.startTransaction();
            List<String> queryDB = new ArrayList<>();
            queryDB.add("Select * from students");
            dbStorage.execute(queryDB);
            dbStorage.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                dbStorage.disconnect();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
