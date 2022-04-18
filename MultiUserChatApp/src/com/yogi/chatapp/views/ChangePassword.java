/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

/*
 * Created by JFormDesigner on Sun Apr 17 00:07:31 IST 2022
 */

package com.yogi.chatapp.views;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.Exceptions.MyException;
import com.yogi.chatapp.db.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * @author Yogesh Satyam
 */
public class ChangePassword extends JFrame {
    public ChangePassword() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void changePwd() {
        String mobile = mobiletxt.getText();
        char[] oldPassword = oldpwdtxt.getPassword(), newPassword = newpwdtxt.getPassword();
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(mobile, oldPassword, newPassword);
        try {
            int result = userDAO.updatePassword(userDTO);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Password Changed Successfully");
                //   System.out.println("Record added");
            } else {
                JOptionPane.showMessageDialog(this, "Password Change failed");

            }
        } catch (MyException e) {
            JOptionPane.showMessageDialog(this, String.valueOf(e));
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }
    }

    private void subitbtn() {
        changePwd();
        dispose();
        UserScreen.main(new String[]{""});
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        JLabel titlelbl = new JLabel();
        JLabel mobilelbl = new JLabel();
        JLabel oldpwdlbl = new JLabel();
        JLabel newpwdlbl = new JLabel();
        JButton subitbtn = new JButton();
        mobiletxt = new JTextField();
        oldpwdtxt = new JPasswordField();
        newpwdtxt = new JPasswordField();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelbl ----
        titlelbl.setText("Change Password");
        titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
        titlelbl.setFont(titlelbl.getFont().deriveFont(titlelbl.getFont().getStyle() | Font.BOLD, titlelbl.getFont().getSize() + 12f));
        contentPane.add(titlelbl);
        titlelbl.setBounds(180, 25, 220, 40);

        //---- mobilelbl ----
        mobilelbl.setText("Mobile No.");
        mobilelbl.setFont(mobilelbl.getFont().deriveFont(mobilelbl.getFont().getStyle() | Font.BOLD, mobilelbl.getFont().getSize() + 10f));
        contentPane.add(mobilelbl);
        mobilelbl.setBounds(25, 140, 125, 35);

        //---- oldpwdlbl ----
        oldpwdlbl.setText("Old Password");
        oldpwdlbl.setFont(oldpwdlbl.getFont().deriveFont(oldpwdlbl.getFont().getStyle() | Font.BOLD, oldpwdlbl.getFont().getSize() + 10f));
        contentPane.add(oldpwdlbl);
        oldpwdlbl.setBounds(25, 195, 145, 35);

        //---- newpwdlbl ----
        newpwdlbl.setText("New Password");
        newpwdlbl.setFont(newpwdlbl.getFont().deriveFont(newpwdlbl.getFont().getStyle() | Font.BOLD, newpwdlbl.getFont().getSize() + 10f));
        contentPane.add(newpwdlbl);
        newpwdlbl.setBounds(25, 245, 155, 35);

        //---- subitbtn ----
        subitbtn.setText("Submit");
        subitbtn.setFont(subitbtn.getFont().deriveFont(subitbtn.getFont().getStyle() | Font.BOLD, subitbtn.getFont().getSize() + 12f));
        subitbtn.addActionListener(e -> subitbtn());
        contentPane.add(subitbtn);
        subitbtn.setBounds(235, 315, 135, 40);

        //---- mobiletxt ----
        mobiletxt.setFont(mobiletxt.getFont().deriveFont(mobiletxt.getFont().getSize() + 9f));
        contentPane.add(mobiletxt);
        mobiletxt.setBounds(230, 140, 210, 35);
        contentPane.add(oldpwdtxt);
        oldpwdtxt.setBounds(230, 195, 210, 35);
        contentPane.add(newpwdtxt);
        newpwdtxt.setBounds(230, 250, 210, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
        setSize(590, 395);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JTextField mobiletxt;
    private JPasswordField oldpwdtxt;
    private JPasswordField newpwdtxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
