package com.yogi.chatapp.db;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.Exceptions.MyException;
import com.yogi.chatapp.utils.Encryption;
import com.yogi.chatapp.utils.Validations;

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
        Connection connection=null;
        PreparedStatement pstmt=null;
        final String sql="insert into users(userid,password,email,mobile,city) values(?,?,?,?,?)";
        try {
            connection = CommonDAO.createConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userDTO.getUserid());
            String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
            pstmt.setString(2,encryptedPwd);
            pstmt.setString(3,userDTO.getEmail());
            pstmt.setString(4, userDTO.getMobile());
            pstmt.setString(5, userDTO.getMobile());
            int record=pstmt.executeUpdate();
            return record;
        }
        finally {
            if(pstmt!=null) {
                pstmt.close();
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

    public int updatePassword(UserDTO userDTO) throws SQLException, ClassNotFoundException, MyException, NoSuchAlgorithmException {
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        final String sqlQuery1="select password from users where mobile=?";
        final String sqlQuery2="update chatdb.users set password=? where mobile=?";
        try {


            connection = CommonDAO.createConnection();
            pstmt = connection.prepareStatement(sqlQuery1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_FORWARD_ONLY);
            pstmt.setString(1, userDTO.getMobile());
            rs = pstmt.executeQuery();
            if (!rs.next())
                throw new MyException("User does not exist");
            rs.beforeFirst();
            String currentPassword = null;
            while (rs.next()) {
                currentPassword = rs.getString(1);
            }
            if (!Validations.matchPwd(currentPassword, new String(userDTO.getOldPassword())))
                throw new MyException("Old Password did not matched, please provide correct one");
            String encryptedPassword = Encryption.passwordEncrypt(new String(userDTO.getNewPassword()));
            pstmt = connection.prepareStatement(sqlQuery2);
            pstmt.setString(1, encryptedPassword);
            pstmt.setString(2, userDTO.getMobile());
            return pstmt.executeUpdate();
        }
        finally {
           if(rs!=null)
               rs.close();
           if(pstmt!=null)
               pstmt.close();
           if(connection!=null)
               connection.close();
        }
    }

}
