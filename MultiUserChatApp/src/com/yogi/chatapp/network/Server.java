/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final ServerSocket serverSocket;
    final static Map<Long,ServerWorker> workers = new ConcurrentHashMap<>();
    public Server() throws IOException, ClassNotFoundException {
        int port = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        serverSocket = new ServerSocket(port);
        System.out.println("Server Started and Waiting for Client to join....");
        handleClientRequest();
    }
    public void handleClientRequest() throws IOException, ClassNotFoundException {
        //noinspection InfiniteLoopStatement
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ServerWorker serverWorker = new ServerWorker(clientSocket);
//            serverWorker.setName(UserInfo.USER_Name);
            workers.put(serverWorker.getId(),serverWorker);
            serverWorker.start();
        }
    }


    /**
     * This is for single client
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
    public static void main(String[] args){
        try {
            new Server();
        } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
        }

    }
}


