package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    public Client() throws IOException {
        int port=Integer.parseInt(ConfigReader.getValue("PORTNO"));
        socket=new Socket(ConfigReader.getValue("SERVER_IP"),port);
        System.out.println("Client Joined");
        System.out.println("Enter the Message:");
        Scanner scanner=new Scanner(System.in);
        String message=scanner.nextLine();
        OutputStream out=socket.getOutputStream();
        out.write(message.getBytes());
        System.out.println("Message sent to the server");
        out.close();
        scanner.close();
        socket.close();
    }
    public static void main(String[] args) throws IOException {
        Client client=new Client();
    }
}
