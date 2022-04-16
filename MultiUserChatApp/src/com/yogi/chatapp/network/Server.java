package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    public Server() throws IOException {
        int port= Integer.parseInt(ConfigReader.getValue("PORTNO"));
        serverSocket=new ServerSocket(port);
        System.out.println("Server Started and Waiting for Client");
        Socket socket=serverSocket.accept(); // HandShaking with client
        System.out.println("Client joins the server ");
        InputStream in=socket.getInputStream();
        byte[] arr=in.readAllBytes();
        String str=new String(arr);
        System.out.println("Message received from client: "+str);
        in.close();
        socket.close();
    }
    public static void main(String[] args) throws IOException {
        Server server=new Server();

    }
}

