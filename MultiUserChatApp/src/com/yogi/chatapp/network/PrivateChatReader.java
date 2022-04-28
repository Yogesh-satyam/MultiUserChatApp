/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.views.PrivateChatScreen;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class PrivateChatReader extends Thread {
    private final InputStream pcin;
    private final JTextArea pchattxtarea;
    private final PrivateChatScreen privateChatScreen;
    public PrivateChatReader(Socket mySocket, PrivateChatScreen privateChatScreen) throws IOException {
        this.pcin=mySocket.getInputStream();
        this.pchattxtarea = privateChatScreen.getPchattxtarea();
        this.privateChatScreen=privateChatScreen;
    }
    @Override
    public void run() {
        String line;
        BufferedReader br=new BufferedReader(new InputStreamReader(pcin));
        try {
            while (true) {
                if(isInterrupted())
                    break;
//                if(br.read()==-1){
//                    quit();
//                    break;
//                }
//                br.reset();
                try {
                    line = br.readLine();
                }catch (SocketException e){
                    e.printStackTrace();
                    break;
                }
                if(line==null){
                    quit();
                    break;
                }
                pchattxtarea.setText(pchattxtarea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
                System.out.println("reader writing");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //noinspection ConstantConditions
                if (pcin != null)
                    pcin.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void quit() {
        privateChatScreen.forceQuitChat();
    }
}
