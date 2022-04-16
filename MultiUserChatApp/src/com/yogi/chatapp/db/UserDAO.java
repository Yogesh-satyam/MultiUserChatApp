package com.yogi.chatapp.db;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.utils.Encryption;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

//User CRUD
public class UserDAO {

    public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        final String sql="select userid from users where userid=? and password=?";
        try {
            con=CommonDAO.createConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,userDTO.getUserid());
            String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
            pstmt.setString(2,encryptedPwd);
            rs=pstmt.executeQuery();
            return rs.next();
        }
        finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
            if(con!=null)
                con=null;
        }
    }

    public int add(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        char password[]={'a','m','i','t'};
        Connection connection=null;
        Statement stmt=null;
        try {
            connection = CommonDAO.createConnection();
            stmt = connection.createStatement();
            int record = stmt.executeUpdate("insert into users(userid,password) values('" + userDTO.getUserid() + "','" + Encryption.passwordEncrypt(new String(userDTO.getPassword())) + "')");
            return record;
        }
        finally {
            if(stmt!=null) {
                stmt.close();
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

}
