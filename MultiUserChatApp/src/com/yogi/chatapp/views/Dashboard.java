/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

/*
 * Created by JFormDesigner on Sun Apr 10 00:43:42 IST 2022
 */

package com.yogi.chatapp.views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Yogesh Satyam
 */
public class Dashboard extends JFrame {

    ClientChatScreen clientChatScreen;
    public Dashboard(String message) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(message);
    }

    private void logout() {
        if(clientChatScreen!=null&&clientChatScreen.isDisplayable()){
            JOptionPane.showMessageDialog(this, "Please close your chat first to logout");
            return;
        }
        this.setVisible(false);
        this.dispose();
        JOptionPane.showMessageDialog(this, "You have been successfully logged out.");
        UserScreen.main(new String[]{""});
    }

    private void startChat() {
        if(clientChatScreen!=null&&clientChatScreen.isDisplayable()){
            JOptionPane.showMessageDialog(this,"Your chat window is already open");
            return;
        }
        try {
           clientChatScreen= new ClientChatScreen();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        JMenuBar menuBar1 = new JMenuBar();
        JMenu chatMenu = new JMenu();
        JMenuItem startChat = new JMenuItem();
        JMenuItem logout = new JMenuItem();
        JLabel label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== chatMenu ========
            {
                chatMenu.setText("Chat");

                //---- startChat ----
                startChat.setText("Start Chat");
                startChat.addActionListener(e -> startChat());
                chatMenu.add(startChat);

                //---- logout ----
                logout.setText("Logout");
                logout.addActionListener(e -> logout());
                chatMenu.add(logout);
            }
            menuBar1.add(chatMenu);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("text");
        //noinspection ConstantConditions
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

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
