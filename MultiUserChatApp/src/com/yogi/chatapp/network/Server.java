package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ServerSocket serverSocket;
    final ArrayList<ServerWorker> workers=new ArrayList<>();

    public Server() throws IOException {
        int port= Integer.parseInt(ConfigReader.getValue("PORTNO"));
        serverSocket=new ServerSocket(port);
        System.out.println("Server Started and Waiting for Client to join....");
        handleClientRequest();

    }
    public void handleClientRequest() throws IOException {
        while(true){
            Socket  clientSocket=serverSocket.accept();
            ServerWorker serverWorker=new ServerWorker(clientSocket,this);
            workers.add(serverWorker);
            serverWorker.start();
        }
    }



    /**
     This is for single client
     */
//    public Server() throws IOException {
//        int port= Integer.parseInt(ConfigReader.getValue("PORTNO"));
//        serverSocket=new ServerSocket(port);
//        System.out.println("Server Started and Waiting for Client");
//        Socket socket=serverSocket.accept(); // HandShaking with client
//        System.out.println("Client joins the server ");
//        InputStream in=socket.getInputStream();
//        byte[] arr=in.readAllBytes();
//        String str=new String(arr);
//        System.out.println("Message received from client: "+str);
//        in.close();
//        socket.close();
//    }
    public static void main(String[] args) throws IOException {
        Server server=new Server();

    }
}

