/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.utils.UserInfo;
import com.yogi.chatapp.views.PrivateChatScreen;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrivateChatServer extends Thread{
    private final Client client;
    private Socket otherClientSocket;
    private final ServerSocket privateChatSocket;
    private InputStream pcin;
    private final Thread writePort;
    private Thread makeCon;
    private PrivateChatScreen privateChatScreen;
    private JTextArea pchattxtarea;
    private BufferedReader br;
    private String senderName;
    private int tester=0;
    public PrivateChatServer(ServerSocket privateChatSocket, Thread writePort, Client client){
        this.privateChatSocket = privateChatSocket;
        this.writePort = writePort;
        this.client=client;
        makeCon=new Thread(() -> {
            try {
                initThread();
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
            System.out.println(UserInfo.getUser_id()+" is talking with "+senderName);
        setPchattxtarea(privateChatScreen.getPchattxtarea());
        String line;
        try {
            while(true){
                if(isInterrupted())
                    break;
//                if(br.read()==-1){
//                    System.out.println("server disconnected");
//                    if(!client.isInitiator())
//                        privateChatScreen.disconnected(senderName);
//                    restartConnection();
//                    break;
//                }
//                br.reset();
                line = br.readLine();
                if(line==null){
                    System.out.println("server disconnected");
                    if(!client.isInitiator())
                        privateChatScreen.disconnected(senderName);
                    restartConnection();
                    break;
                }
                pchattxtarea.setText(pchattxtarea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
                System.out.println("server reading");
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
        System.out.println("drop connection called");
        if(otherClientSocket.isConnected())
            otherClientSocket.close();
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
                throw new RuntimeException(e);
            }
    }
    private void restartConnection(){
        makeCon=new Thread(() -> {
            try {
                initThread();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        makeCon.start();
        Executors.newSingleThreadScheduledExecutor().schedule(this,300, TimeUnit.MILLISECONDS);
    }
    private void initThread() throws IOException, InterruptedException {
        System.out.println((tester++>1?"2nd time":"first time"));
        acceptConnection();
        pcin=otherClientSocket.getInputStream();
        OutputStream pcout = otherClientSocket.getOutputStream();
        br=new BufferedReader(new InputStreamReader(pcin));
        senderName= br.readLine();
        System.out.println(senderName);
        if(client.getConfirmation(senderName))
            privateChatScreen =new PrivateChatScreen(client,this, pcout);
        else {
            System.out.println("con drooped");
            dropConnection();
        }
        if(tester>1)
            System.out.println("2nd time ran successfully");
    }
    //    private void setPcin(InputStream pcin) {
//        this.pcin = pcin;
//    }
}
