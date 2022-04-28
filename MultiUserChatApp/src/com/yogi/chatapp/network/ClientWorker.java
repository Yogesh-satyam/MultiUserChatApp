/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Client Data Read
public class ClientWorker extends Thread {
    private final InputStream in;
    private final JTextArea textArea;
    private final ObjectInputStream ois;
    private final Thread onlineUsersThread;
    private final ScheduledExecutorService executor;
    private ArrayList<String> activeClients;

//    private final Thread clientHelper;

    public ClientWorker(InputStream in, JTextArea textArea, DefaultListModel<String> onlineUsersList) throws IOException {
        this.in = in;
        this.textArea = textArea;
        ois = new ObjectInputStream(in);
//        clientHelper = new ClientHelper(in);
        onlineUsersThread = new Thread(() -> {
            writeActiveClients(onlineUsersList);
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
        });
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void run() {
//        clientHelper.start();
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        boolean runOnce = false;
        String line;
        try {
            while (!isInterrupted()) {
                try {
                //                line = br.readLine();
                    line = (String) ois.readObject();
                    //noinspection unchecked
                    activeClients = (ArrayList<String>) ois.readObject();
                }catch (SocketException se){
                    break;
                }
                textArea.setText(textArea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
                if (!runOnce) {
//                    onlineUsersThread.start();
//                    executor.scheduleWithFixedDelay(onlineUsersThread, 0, 5000, TimeUnit.MILLISECONDS);
                    executor.scheduleAtFixedRate(onlineUsersThread, 0, 10000, TimeUnit.MILLISECONDS);
                    runOnce = true;
                }
//                writeActiveClients(activeClients);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
//                if (br != null) {
//                    br.close();
//                }
                executor.shutdownNow();
                onlineUsersThread.interrupt();
                if (ois != null) {
                    ois.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeActiveClients(DefaultListModel<String> onlineUsersList) {
        if (onlineUsersList != null && !onlineUsersList.isEmpty()) {
            onlineUsersList.removeAllElements();
        }

        for (String name : activeClients) {
            //noinspection ConstantConditions
            onlineUsersList.addElement(name);
        }
    }
}

//class ClientHelper extends Thread {
//    private final ObjectInputStream ois;
//    private ArrayList activeClients;
//    ClientHelper(InputStream in) throws IOException {
//        ois = new ObjectInputStream(in);
//    }
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                if( ! (ois.readObject() instanceof ArrayList)){
//                    continue;
//                }
//                activeClients = (ArrayList) ois.readObject();
//                writeActiveClients(activeClients);
//                Thread.sleep(10000);
//            }
//        } catch (InterruptedException | IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void writeActiveClients(ArrayList activeClients) {
//        for(Object obj:activeClients)
//            System.out.println(obj);
//    }
//}
