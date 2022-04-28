/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

/*
 * Created by JFormDesigner on Wed Apr 27 20:19:26 IST 2022
 */

package com.yogi.chatapp.views;

import com.yogi.chatapp.network.Client;
import com.yogi.chatapp.utils.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Yogesh Satyam
 */
public class PrivateChatScreen extends JFrame {
    private final Client client;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yogesh Satyam
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem quitPChat;
    private JScrollPane scrollPane1;
    private JTextArea pchattxtarea;
    private JTextField messagetxtfld;
    private JButton sendIt;
    private JMenu menu1;
    private Thread privateChatThread;
    private final OutputStream pcout;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public PrivateChatScreen(Client client, Thread privateChatThread, OutputStream pcout) {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.client = client;
        this.privateChatThread=privateChatThread;
        this.pcout=pcout;
    }

    public JTextArea getPchattxtarea() {
        return pchattxtarea;
    }

    private void quitChat(ActionEvent e) {
        privateChatThread.interrupt();
        if(client.isInitiator()) {
            try {
                client.stopPCReader();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        client.setInitiator(false);
        }
        this.dispose();
        this.setVisible(false);
    }

    private void sendIt(ActionEvent e) {
        String message = messagetxtfld.getText();
        pchattxtarea.setText(pchattxtarea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") +"you - "+message + "\n");
        try {
            if(client.isInitiator())
                client.sendMessageTo(UserInfo.getUser_id() + " - " + message);
            else
                client.sendMessageTo(UserInfo.getUser_id() + " - " + message,pcout);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        messagetxtfld.setText(null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        quitPChat = new JMenuItem();
        scrollPane1 = new JScrollPane();
        pchattxtarea = new JTextArea();
        messagetxtfld = new JTextField();
        sendIt = new JButton();
        menu1 = new JMenu();

        //======== this ========
        setTitle("GaapShap");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Options");

                //---- quitPChat ----
                quitPChat.setText("Quit Chat");
                quitPChat.addActionListener(e -> quitChat(e));
                menu2.add(quitPChat);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(pchattxtarea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 30, 510, 265);

        //---- messagetxtfld ----
        messagetxtfld.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        contentPane.add(messagetxtfld);
        messagetxtfld.setBounds(50, 315, 350, 40);

        //---- sendIt ----
        sendIt.setText("Send Message");
        sendIt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        sendIt.addActionListener(e -> sendIt(e));
        contentPane.add(sendIt);
        sendIt.setBounds(425, 315, 140, 40);

        //======== menu1 ========
        {
            menu1.setText("text");
        }
        contentPane.add(menu1);
        menu1.setBounds(new Rectangle(new Point(15, -25), menu1.getPreferredSize()));

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
        setSize(615, 430);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void disconnected(String senderName) {
        JOptionPane.showMessageDialog(this,senderName+" quit the chat or might be disconnected.");
        this.dispose();
        this.setVisible(false);
    }
}
