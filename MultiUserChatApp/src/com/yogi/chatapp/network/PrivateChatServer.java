/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.views.PrivateChatScreen;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrivateChatServer extends Thread{
    private Socket otherClientSocket;
    private final ServerSocket privateChatSocket;
    private InputStream pcin;
    private OutputStream pcout;
    private final Thread writePort;
    private Thread makeCon;
    private PrivateChatScreen privateChatScreen;
    private JTextArea pchattxtarea;
    private BufferedReader br;
    private String senderName;
    public PrivateChatServer(ServerSocket privateChatSocket, Thread writePort, Client client){
        this.privateChatSocket = privateChatSocket;
        this.writePort = writePort;
        makeCon=new Thread(() -> {
            try {
                acceptConnection();
                pcin=otherClientSocket.getInputStream();
                pcout=otherClientSocket.getOutputStream();
                br=new BufferedReader(new InputStreamReader(pcin));
                senderName= br.readLine();
                System.out.println(senderName);
                if(client.getConfirmation(senderName))
                    privateChatScreen =new PrivateChatScreen(client,this,pcout);
                else {
                    System.out.println("con drooped");
                    dropConnection();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        makeCon.start();
        Executors.newSingleThreadScheduledExecutor().schedule(this,300, TimeUnit.MILLISECONDS);
    }

    private void acceptConnection() throws IOException, InterruptedException {
        writePort.join();
        int count=0;
        while(count<1){
            otherClientSocket=privateChatSocket.accept();
            count++;
        }
        System.out.println("connection established");
    }


    @Override
    public void run() {
        try {
            makeCon.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(otherClientSocket.isConnected())
            System.out.println("user is talking with "+senderName);
        setPchattxtarea(privateChatScreen.getPchattxtarea());
        String line;
        try {
            while(true){
                if(isInterrupted())
                    break;
                if(otherClientSocket.isClosed())
                    break;
                try {
                    line = br.readLine();
                }catch (SocketException e){
                    /**
                     * here somehow we gets socket Exception
                     * */
//                    e.printStackTrace();
                    privateChatScreen.disconnected(senderName);
                    otherClientSocket=null;
                    senderName=null;
                    restartConnection();
                    break;
                }
                pchattxtarea.setText(pchattxtarea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if(pcin!=null){
                    pcin.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void dropConnection() throws IOException{
        if(otherClientSocket.isConnected())
            otherClientSocket.close();
        otherClientSocket=null;
        senderName=null;
        restartConnection();
    }

    private void setPchattxtarea(JTextArea pchattxtarea) {
        this.pchattxtarea = pchattxtarea;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            dropConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection(){
        makeCon=new Thread(makeCon);
        makeCon.start();
        Executors.newSingleThreadScheduledExecutor().schedule(this,300, TimeUnit.MILLISECONDS);
    }

    //    private void setPcin(InputStream pcin) {
//        this.pcin = pcin;
//    }
}
