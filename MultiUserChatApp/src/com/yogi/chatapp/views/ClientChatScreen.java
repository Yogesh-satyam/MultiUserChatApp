/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

/*
 * Created by JFormDesigner on Mon Apr 18 00:35:48 IST 2022
 */

package com.yogi.chatapp.views;

import com.yogi.chatapp.Exceptions.MyException;
import com.yogi.chatapp.network.Client;
import com.yogi.chatapp.utils.UserInfo;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yogesh Satyam
 */
public class ClientChatScreen extends JFrame {
    private final Client client;
    private final DefaultListModel<String> onlineUsersList;
    public ClientChatScreen() throws IOException {
        onlineUsersList=new DefaultListModel<>();
        initComponents();
        onlineUsersList.addElement("Send First Message to View");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        client = new Client(chattxtarea, onlineUsersList,this);
    }

//    public static void main(String[] args) {
//        try {
//            ClientChatScreen clientChatScreen=new ClientChatScreen();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void sendIt() {
        String message = messagetxtfld.getText();
        try {
            client.sendMessage(UserInfo.getUser_id() + " - " + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        messagetxtfld.setText(null);
    }

    private void quitChat() {
        try {
            client.disconnect();
        } catch (IOException | InterruptedException | MyException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.dispose();
        this.setVisible(false);

    }
    private void chatWith() {
        String userid=JOptionPane.showInputDialog(this,"Are you sure you want exit group chat and start private chat?\nIf yes, write complete userid with whom you want to chat.","Chat With",JOptionPane.QUESTION_MESSAGE);
        if(userid==null||userid.equalsIgnoreCase(UserInfo.getUser_id())|| userid.equals("")) {
            if (userid == null) {
                return;
            } else if (userid.equals("")) {
                JOptionPane.showMessageDialog(this, "Please provide userid of other person, it can't be vacant or null");
                return;
            } else {
                JOptionPane.showMessageDialog(this, "You can't start private chat with yourself!");
                return;
            }
        }
        try {
            quitChat();
//            client.quitGroupChat();
//            this.dispose();
//            this.setVisible(false);
            client.initiatePrivateChat(userid);
        } catch (MyException e) {
            JOptionPane.showMessageDialog(this,String.valueOf(e));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menu2 = new JMenu();
        JMenuItem chatWith = new JMenuItem();
        JMenuItem quitChat = new JMenuItem();
        JScrollPane scrollPane1 = new JScrollPane();
        chattxtarea = new JTextArea();
        messagetxtfld = new JTextField();
        JButton sendIt = new JButton();
        JMenu menu1 = new JMenu();
        JScrollPane scrollPane2 = new JScrollPane();
        @SuppressWarnings("rawtypes") JList onlineList = new JList();
        JLabel label1 = new JLabel();

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

                //---- chatWith ----
                chatWith.setText("Chat With");
                chatWith.addActionListener(e -> chatWith());
                menu2.add(chatWith);

                //---- quitChat ----
                quitChat.setText("Quit Chat");
                quitChat.addActionListener(e -> quitChat());
                menu2.add(quitChat);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(chattxtarea);
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
        sendIt.addActionListener(e -> sendIt());
        contentPane.add(sendIt);
        sendIt.setBounds(425, 315, 140, 40);

        //======== menu1 ========
        {
            menu1.setText("text");
        }
        contentPane.add(menu1);
        menu1.setBounds(new Rectangle(new Point(15, -25), menu1.getPreferredSize()));

        //======== scrollPane2 ========
        {

            //---- onlineList ----
            onlineList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            //noinspection unchecked
            onlineList.setModel(onlineUsersList);
            scrollPane2.setViewportView(onlineList);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(575, 55, 155, 240);

        //---- label1 ----
        label1.setText("Online Users");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        label1.setBackground(new Color(187, 187, 187));
        label1.setLabelFor(onlineList);
        label1.setFocusable(false);
        label1.setBorder(new EtchedBorder());
        contentPane.add(label1);
        label1.setBounds(575, 30, 155, 25);

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
        setSize(760, 430);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JTextArea chattxtarea;
    private JTextField messagetxtfld;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
