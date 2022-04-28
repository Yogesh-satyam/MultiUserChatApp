/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class PrivateChatReader extends Thread {
    private final InputStream pcin;
    private final Socket mySocket;
    private final JTextArea pchattxtarea;
    public PrivateChatReader(Socket mySocket, JTextArea pchattxtarea) throws IOException {
        this.mySocket=mySocket;
        this.pcin=mySocket.getInputStream();
        this.pchattxtarea = pchattxtarea;
    }
    @Override
    public void run() {
        String line;
        BufferedReader br=new BufferedReader(new InputStreamReader(pcin));
        try {
            while (true) {
                if(isInterrupted())
                    break;
                if(mySocket.isClosed())
                    break;
                try {
                    line = br.readLine();
                }catch (SocketException e){
                    e.printStackTrace();
                    break;
                }
                pchattxtarea.setText(pchattxtarea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
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
}
