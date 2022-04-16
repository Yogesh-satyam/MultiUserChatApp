package com.yogi.chatapp.views;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.db.UserDAO;

import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Yogesh Satyam
 */
public class UserScreen extends JFrame {

    public UserScreen() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        UserScreen window = new UserScreen();
    }
    UserDAO userDAO=new UserDAO();
    private void doLogin(){
        String userid=useridtxt.getText();
        char[] password=passwordField1.getPassword();
        UserDTO userDTO=new UserDTO(userid,password);
        try {
            String message="";
            if(userDAO.isLogin(userDTO)) {
                message = "Welcome " + userid;
                JOptionPane.showMessageDialog(this,message);
                setVisible(false);
                dispose();
                Dashboard dashboard=new Dashboard(message);
                dashboard.setVisible(true);
            }else {
                message = "Invalid Userid or Password";
                JOptionPane.showMessageDialog(this,message);
            }
 //           JOptionPane.showMessageDialog(this,message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private void register(){
        String userid=useridtxt.getText();
        char[] password=passwordField1.getPassword();

        UserDTO userDTO=new UserDTO(userid,password);
        try {
            int result=userDAO.add(userDTO);
            if(result>0) {
                JOptionPane.showMessageDialog(this, "Registered Successfully");
             //   System.out.println("Record added");
            }
            else {
                JOptionPane.showMessageDialog(this,"Register failed");

            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("DB issue.........");
            throwables.printStackTrace();
        } catch (Exception ex){
            System.out.println("Some generic exception raised...");
            ex.printStackTrace();
        }
    }

    private void registerbtn(ActionEvent e) {
        register();
    }

    private void loginbtn(ActionEvent e) {
        doLogin();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        label1 = new JLabel();
        useridtxt = new JTextField();
        useridlbl = new JLabel();
        pswdlbl = new JLabel();
        loginbtn = new JButton();
        registerbtn = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("LOGIN");
        setBackground(Color.black);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("LOGIN");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        contentPane.add(label1);
        label1.setBounds(190, 20, 166, 40);
        contentPane.add(useridtxt);
        useridtxt.setBounds(275, 95, 235, 35);

        //---- useridlbl ----
        useridlbl.setText("Userid");
        useridlbl.setFont(useridlbl.getFont().deriveFont(useridlbl.getFont().getStyle() | Font.BOLD, useridlbl.getFont().getSize() + 12f));
        useridlbl.setLabelFor(useridtxt);
        contentPane.add(useridlbl);
        useridlbl.setBounds(75, 100, 110, 35);

        //---- pswdlbl ----
        pswdlbl.setText("Password");
        pswdlbl.setFont(pswdlbl.getFont().deriveFont(pswdlbl.getFont().getStyle() | Font.BOLD, pswdlbl.getFont().getSize() + 12f));
        contentPane.add(pswdlbl);
        pswdlbl.setBounds(75, 160, 140, 35);

        //---- loginbtn ----
        loginbtn.setText("Login");
        loginbtn.setFont(loginbtn.getFont().deriveFont(loginbtn.getFont().getStyle() | Font.BOLD, loginbtn.getFont().getSize() + 12f));
        loginbtn.setFocusPainted(false);
        loginbtn.addActionListener(e -> loginbtn(e));
        contentPane.add(loginbtn);
        loginbtn.setBounds(120, 255, 110, 40);

        //---- registerbtn ----
        registerbtn.setText("Register");
        registerbtn.setFont(registerbtn.getFont().deriveFont(registerbtn.getFont().getStyle() | Font.BOLD, registerbtn.getFont().getSize() + 12f));
        registerbtn.setFocusPainted(false);
        registerbtn.addActionListener(e -> registerbtn(e));
        contentPane.add(registerbtn);
        registerbtn.setBounds(265, 255, 155, 40);
        contentPane.add(passwordField1);
        passwordField1.setBounds(275, 160, 235, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(580, 365);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yogesh Satyam
    private JLabel label1;
    private JTextField useridtxt;
    private JLabel useridlbl;
    private JLabel pswdlbl;
    private JButton loginbtn;
    private JButton registerbtn;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
