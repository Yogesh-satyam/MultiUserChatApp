/*
 * Created by JFormDesigner on Sun Apr 10 00:43:42 IST 2022
 */

package com.yogi.chatapp.views;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Yogesh Satyam
 */
public class Dashboard extends JFrame {
    public Dashboard(String message) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(message);
    }

    private void logout(ActionEvent e) {
        setVisible(false);
        dispose();
        JOptionPane.showMessageDialog(this,"You have been successfully logged out.");
        UserScreen.main(new String[]{""});
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        menuBar1 = new JMenuBar();
        chatMenu = new JMenu();
        startChat = new JMenuItem();
        logout = new JMenuItem();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== chatMenu ========
            {
                chatMenu.setText("Chat");

                //---- startChat ----
                startChat.setText("Start Chat");
                chatMenu.add(startChat);

                //---- logout ----
                logout.setText("Logout");
                logout.addActionListener(e -> logout(e));
                chatMenu.add(logout);
            }
            menuBar1.add(chatMenu);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("text");
        label1.setIcon(new ImageIcon(getClass().getResource("/images/960x0.jpg")));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(725, 500));
        label1.setMaximumSize(new Dimension(724, 679));
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(label1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yogesh Satyam
    private JMenuBar menuBar1;
    private JMenu chatMenu;
    private JMenuItem startChat;
    private JMenuItem logout;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
