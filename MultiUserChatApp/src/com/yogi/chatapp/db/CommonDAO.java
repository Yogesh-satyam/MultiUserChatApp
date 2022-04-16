package com.yogi.chatapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.yogi.chatapp.utils.ConfigReader.getValue;

public interface CommonDAO {

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(getValue("DRIVER"));
        final String CONNECTION_STRING = getValue("CONNECTION_URL");
        final String USER_ID = getValue("USERID");
        final String PASSWORD = getValue("PASSWORD");
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
        return connection;
    }

}
