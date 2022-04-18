package com.yogi.chatapp.views;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.db.UserDAO;
import com.yogi.chatapp.utils.UserInfo;

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
            String message;
            if(userDAO.isLogin(userDTO)) {
                message = "Welcome " + userid;
                UserInfo.USER_Name=userid;
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
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }
    }
    private void register(){
        String userid=useridtxt.getText(),email=emailtxt.getText(),mobile=mobiletxt.getText(),city=citytxt.getText();
        char[] password=passwordField1.getPassword();
        if(userid.equals("")||(new String(password).equals(""))||mobile.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Fill The Form To Register");
            return;
        }
        UserDTO userDTO=new UserDTO(userid,password,email,mobile,city);
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

    private void clearbtn(ActionEvent e) {
        useridtxt.setText(null);
        passwordField1.setText(null);
        emailtxt.setText(null);
        mobiletxt.setText(null);
        citytxt.setText(null);
    }

    private void changepwdbtn(ActionEvent e) {
        dispose();
        ChangePassword changePassword=new ChangePassword();
        changePassword.setVisible(true);
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
        emaillbl = new JLabel();
        emailtxt = new JTextField();
        mobilelbl = new JLabel();
        mobiletxt = new JTextField();
        citylbl = new JLabel();
        citytxt = new JTextField();
        clearbtn = new JButton();
        changepwdbtn = new JButton();

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
        label1.setBounds(300, 20, 166, 40);
        contentPane.add(useridtxt);
        useridtxt.setBounds(215, 95, 235, 35);

        //---- useridlbl ----
        useridlbl.setText("Userid");
        useridlbl.setFont(useridlbl.getFont().deriveFont(useridlbl.getFont().getStyle() | Font.BOLD, useridlbl.getFont().getSize() + 12f));
        useridlbl.setLabelFor(useridtxt);
        contentPane.add(useridlbl);
        useridlbl.setBounds(15, 100, 110, 35);

        //---- pswdlbl ----
        pswdlbl.setText("Password");
        pswdlbl.setFont(pswdlbl.getFont().deriveFont(pswdlbl.getFont().getStyle() | Font.BOLD, pswdlbl.getFont().getSize() + 12f));
        contentPane.add(pswdlbl);
        pswdlbl.setBounds(15, 155, 140, 35);

        //---- loginbtn ----
        loginbtn.setText("Login");
        loginbtn.setFont(loginbtn.getFont().deriveFont(loginbtn.getFont().getStyle() | Font.BOLD, loginbtn.getFont().getSize() + 12f));
        loginbtn.setFocusPainted(false);
        loginbtn.addActionListener(e -> loginbtn(e));
        contentPane.add(loginbtn);
        loginbtn.setBounds(235, 415, 110, 40);

        //---- registerbtn ----
        registerbtn.setText("Register");
        registerbtn.setFont(registerbtn.getFont().deriveFont(registerbtn.getFont().getStyle() | Font.BOLD, registerbtn.getFont().getSize() + 12f));
        registerbtn.setFocusPainted(false);
        registerbtn.addActionListener(e -> registerbtn(e));
        contentPane.add(registerbtn);
        registerbtn.setBounds(50, 415, 155, 40);
        contentPane.add(passwordField1);
        passwordField1.setBounds(215, 155, 235, 35);

        //---- emaillbl ----
        emaillbl.setText("E-mail");
        emaillbl.setFont(emaillbl.getFont().deriveFont(emaillbl.getFont().getStyle() | Font.BOLD, emaillbl.getFont().getSize() + 12f));
        emaillbl.setLabelFor(emailtxt);
        contentPane.add(emaillbl);
        emaillbl.setBounds(15, 220, 110, 35);
        contentPane.add(emailtxt);
        emailtxt.setBounds(215, 215, 235, 35);

        //---- mobilelbl ----
        mobilelbl.setText("Mobile No.");
        mobilelbl.setFont(mobilelbl.getFont().deriveFont(mobilelbl.getFont().getStyle() | Font.BOLD, mobilelbl.getFont().getSize() + 12f));
        mobilelbl.setLabelFor(mobiletxt);
        contentPane.add(mobilelbl);
        mobilelbl.setBounds(15, 280, 140, 35);
        contentPane.add(mobiletxt);
        mobiletxt.setBounds(215, 275, 235, 35);

        //---- citylbl ----
        citylbl.setText("City");
        citylbl.setFont(citylbl.getFont().deriveFont(citylbl.getFont().getStyle() | Font.BOLD, citylbl.getFont().getSize() + 12f));
        citylbl.setLabelFor(citytxt);
        contentPane.add(citylbl);
        citylbl.setBounds(15, 335, 140, 35);
        contentPane.add(citytxt);
        citytxt.setBounds(215, 335, 235, 35);

        //---- clearbtn ----
        clearbtn.setText("Clear");
        clearbtn.setFont(clearbtn.getFont().deriveFont(clearbtn.getFont().getStyle() | Font.BOLD, clearbtn.getFont().getSize() + 12f));
        clearbtn.setFocusPainted(false);
        clearbtn.addActionListener(e -> clearbtn(e));
        contentPane.add(clearbtn);
        clearbtn.setBounds(380, 415, 110, 40);

        //---- changepwdbtn ----
        changepwdbtn.setText("Change Password");
        changepwdbtn.addActionListener(e -> changepwdbtn(e));
        contentPane.add(changepwdbtn);
        changepwdbtn.setBounds(575, 185, 140, changepwdbtn.getPreferredSize().height);

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
        setSize(765, 505);
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
    private JLabel emaillbl;
    private JTextField emailtxt;
    private JLabel mobilelbl;
    private JTextField mobiletxt;
    private JLabel citylbl;
    private JTextField citytxt;
    private JButton clearbtn;
    private JButton changepwdbtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
