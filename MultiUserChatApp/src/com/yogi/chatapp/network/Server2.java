/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

/**
 * DO NOT MODIFY THIS FILE, IT'S UNDER MODIFICATION
 * ALSO THIS FILE HAS NOT BEEN ANYWHERE TILL NOW
**/
package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    private final ServerSocket server2Socket;
    private Socket server1Socket;
    private boolean connected=false;

    public Server2() throws IOException {
        int port=Integer.parseInt(ConfigReader.getValue("SERVER2_PORTNO"));
        this.server2Socket = new ServerSocket(port);
        System.out.println("Server 2 started waiting for Server 1 to get Connected... ");
//        server1Socket=server2Socket.accept();
        connectWithServer1();
        handleClientStatus();
    }

    private void handleClientStatus() {
        while(true){

        }
    }

    private void connectWithServer1() throws IOException {
        while(!connected){
            server1Socket=server2Socket.accept();
            if(server1Socket.isConnected()) {
                connected = true;
                System.out.println("Server 1 connected with server 2. Waiting for Clients to join.... ");
            }
        }
    }

    public static void main(String[] args){
        try {
            Server2 server2=new Server2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
