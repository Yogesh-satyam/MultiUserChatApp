package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private final Socket clientSocket;
    private final OutputStream out;
    private final InputStream in;
    private ClientWorker clientWorker;
    private final JTextArea textArea;

    public Client(JTextArea textArea) throws IOException {
        int port=Integer.parseInt(ConfigReader.getValue("PORTNO"));
        clientSocket =new Socket(ConfigReader.getValue("SERVER_IP"),port);
        in= clientSocket.getInputStream();
        out= clientSocket.getOutputStream();
        this.textArea=textArea;
        readMeassage();
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
        message=message+"\n";
        out.write(message.getBytes());

    }

    public void readMeassage(){
        clientWorker=new ClientWorker(in,textArea);
        clientWorker.start();
    }
}
