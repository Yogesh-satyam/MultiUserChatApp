/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.db.UserDAO;
import com.yogi.chatapp.utils.UserInfo;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerWorker extends Thread {
    private final Socket clientSocket;
    private final InputStream in;
    private final OutputStream out;
    private final ObjectOutputStream oos;
    private final Thread onlineUsersThread;
    private final ObjectInputStream ois;
    private final ScheduledExecutorService executor;
    private final UserDAO userDAO;
    private UserInfo userInfo;
//    private final Thread severHelper;
    private ArrayList<String> activeClients;
    private UserDTO userDTO;

    public ServerWorker(Socket clientSocket) throws IOException, ClassNotFoundException {
        this.clientSocket = clientSocket;
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(out);
        ois = new ObjectInputStream(in);
        userDAO = new UserDAO();
        init(in);
        this.setName(userInfo.getName());
        System.out.println(this.getName() + " New Client Comes");
//        severHelper =new ServerHelper(out);
        onlineUsersThread = new Thread(() -> {
            activeClients = new ArrayList<>();
            getClientNames(activeClients);
        });
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void run() {
//        severHelper.start();
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        executor.scheduleAtFixedRate(onlineUsersThread, 0, 7500, TimeUnit.MILLISECONDS);
        String line;
        try {
            new Thread(() -> {
                try {
                    onlineStatus();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
            while (!clientSocket.isClosed()) {
//                line = br.readLine()+"\n";
                try {
                    line = (String) ois.readObject();
                } catch (EOFException | SocketException e) {
                    break;
                }
//                getClientNames();
                if (line == null || line.equalsIgnoreCase("quit")) {
                    break;
                }

                //out.write(line.getBytes());  //Client Send
                //Broadcast to all
                for (ServerWorker serverWorker : Server.workers.values()) {
                    serverWorker.oos.writeObject(line);
                    serverWorker.oos.writeObject(activeClients);
                    oos.flush();
//                    serverWorker.out.write(line.getBytes());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Server.workers.remove(this.getId());
            try {
                offlineStatus();
                System.out.println(this.getName() + "  Left");
//                for (ServerWorker serverWorker : Server.workers.values()) {
//                    serverWorker.out.write((this.getName() + "  left the chat").getBytes());
//                    out.flush();
//                }
//                if (br != null) {
//                    br.close();
//                }
                executor.shutdownNow();
                onlineUsersThread.interrupt();
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void getClientNames(ArrayList<String> activeClients) {
        for (ServerWorker serverWorker : Server.workers.values()) {
            activeClients.add(serverWorker.getName());
        }
    }

    private void init(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        userInfo = (UserInfo) ois.readObject();
    }

    private void offlineStatus() throws SQLException, ClassNotFoundException {
        userDTO.setStatus('N');
        userDAO.updateStatus(userDTO);
    }

    private void onlineStatus() throws SQLException, ClassNotFoundException {
        userDTO = new UserDTO(userInfo.getName(), 'A');
        userDAO.updateStatus(userDTO);
    }
}

//class ServerHelper extends Thread {
//    private final ObjectOutputStream oos;
//    private ArrayList activeClients;
//    ServerHelper(OutputStream out) throws IOException {
//        oos = new ObjectOutputStream(out);
//        oos.flush();
//        activeClients = new ArrayList();
//    }
//
//    @Override
//    public void run() {
//        try {
//            //noinspection InfiniteLoopStatement
//            while (true) {
//                getClientNames();
//                oos.writeObject(activeClients);
//                oos.flush();
//                Thread.sleep(10000);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getClientNames() {
//        for (ServerWorker serverWorker : Server.workers.values()) {
//            activeClients.add(serverWorker.getName());
//        }
//    }
//}
