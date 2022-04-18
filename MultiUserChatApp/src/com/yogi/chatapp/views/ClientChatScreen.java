/*
 * Created by JFormDesigner on Mon Apr 18 00:35:48 IST 2022
 */

package com.yogi.chatapp.views;

import com.yogi.chatapp.network.Client;
import com.yogi.chatapp.utils.UserInfo;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

/**
 * @author Yogesh Satyam
 */
public class ClientChatScreen extends JFrame {
    private final Client client;

    public ClientChatScreen() throws IOException {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        client=new Client(chattxtarea);
    }

//    public static void main(String[] args) {
//        try {
//            ClientChatScreen clientChatScreen=new ClientChatScreen();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void sendIt(ActionEvent e) {
        String message=messagetxtfld.getText();
        try {
            client.sendMessage(UserInfo.USER_Name+" - "+message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        messagetxtfld.setText(null);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        scrollPane1 = new JScrollPane();
        chattxtarea = new JTextArea();
        messagetxtfld = new JTextField();
        sendIt = new JButton();

        //======== this ========
        setTitle("GaapShap");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

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
        sendIt.addActionListener(e -> sendIt(e));
        contentPane.add(sendIt);
        sendIt.setBounds(425, 315, 140, 40);

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
        setSize(640, 415);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yogesh Satyam
    private JScrollPane scrollPane1;
    private JTextArea chattxtarea;
    private JTextField messagetxtfld;
    private JButton sendIt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
