/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;
import com.yogi.chatapp.utils.UserInfo;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    final Socket clientSocket;
    private final OutputStream out;
    private final InputStream in;
    private final JTextArea textArea;
    private final ObjectOutputStream oos;
    private ClientWorker clientWorker;
    private final DefaultListModel<String> onlineUsersList;

    public Client(JTextArea textArea, DefaultListModel<String> onlineUsersList) throws IOException {
        int port = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        clientSocket = new Socket(ConfigReader.getValue("SERVER_IP"), port);
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(out);
        this.textArea = textArea;
        this.onlineUsersList = onlineUsersList;
        sendClientDetails();
        readMessage();
//        System.out.println("Client Joined");
//        System.out.println("Enter the Message:");
//        Scanner scanner=new Scanner(System.in);
//        String message=scanner.nextLine();
//        OutputStream out=socket.getOutputStream();
//        out.write(message.getBytes());
//        System.out.println("Message sent to the server");
//        out.close();
//        scanner.close();
//        socket.close();
    }

    public void sendMessage(String message) throws IOException {
        message = message + "\n";
//        out.write(message.getBytes());
        oos.writeObject(message);

    }

    public void readMessage() throws IOException {
        clientWorker = new ClientWorker(in, textArea, onlineUsersList);
        clientWorker.start();
    }

    public void disconnect() throws IOException, InterruptedException {
//        in.close();
//        out.close();
        this.clientWorker.interrupt();
        /*        holding further execution until clientWorker thread gets interrupted */
        //noinspection StatementWithEmptyBody
        while (!clientWorker.isInterrupted()) {
        }
        this.clientSocket.close();
    }

    private void sendClientDetails() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(UserInfo.getUserInfoObjet(UserInfo.getUser_id()));
        oos.flush();
    }
}
