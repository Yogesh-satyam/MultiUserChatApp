/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.db;

import com.yogi.chatapp.Exceptions.MyException;
import com.yogi.chatapp.utils.Encryption;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class PortMLR {

    private PortMLR(){
    }
    public static void writePort(String key,int portno) throws IOException, SQLException, ClassNotFoundException, MyException {
        Connection con=null;
        PreparedStatement pstmt = null;
        final String sql = "insert into chatdb.portlist(userid,portno) values(?,?)";
        //noinspection TryFinallyCanBeTryWithResources
        try {
            con = CommonDAO.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, key);
            pstmt.setInt(2,portno);
            if(!(pstmt.executeUpdate()>0))
                throw new MyException("Error occurred while insertion of port number.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public static void removePort(String key) throws SQLException, ClassNotFoundException, MyException {
        Connection con=null;
        PreparedStatement pstmt = null;
        final String sql = "delete from chatdb.portlist where userid=?";
        //noinspection TryFinallyCanBeTryWithResources
        try {
            con = CommonDAO.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, key);
            if(!(pstmt.executeUpdate()>0))
                throw new MyException("Error occurred while deletion of port number.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public static String getPort(String key) throws SQLException, ClassNotFoundException, MyException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        final String sqlQuery="select portno from chatdb.portlist where userid=?";
        //noinspection TryFinallyCanBeTryWithResources
        try{
            connection=CommonDAO.createConnection();
            pstmt=connection.prepareStatement(sqlQuery);
            pstmt.setString(1,key);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
            else
                throw new  MyException("User with userid "+key+" not found.");
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
