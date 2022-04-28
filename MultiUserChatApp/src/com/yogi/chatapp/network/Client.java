/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.network;

import com.yogi.chatapp.DTO.UserDTO;
import com.yogi.chatapp.Exceptions.MyException;
import com.yogi.chatapp.db.UserDAO;
import com.yogi.chatapp.utils.ConfigReader;
import com.yogi.chatapp.db.PortMLR;
import com.yogi.chatapp.utils.UserInfo;
import com.yogi.chatapp.views.ClientChatScreen;
import com.yogi.chatapp.views.PrivateChatScreen;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Client {
    private boolean initiator=false;
    private Thread PCReader;
    private Socket mySocket;
    private final Socket clientSocket;
    private ServerSocket privateChatSocket;
    private final OutputStream out;
    private OutputStream pcout;
    private final InputStream in;
    private final JTextArea textArea;
    private final ObjectOutputStream oos;
    private ClientWorker clientWorker;
    private final DefaultListModel<String> onlineUsersList;
    private final ClientChatScreen clientChatScreen;
    private Thread privateChatThread;
    public Client(JTextArea textArea, DefaultListModel<String> onlineUsersList, ClientChatScreen clientChatScreen) throws IOException {
        int serverPort = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        clientSocket = new Socket(ConfigReader.getValue("SERVER_IP"), serverPort);
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
        oos=new ObjectOutputStream(out);
        this.clientChatScreen=clientChatScreen;
        this.textArea=textArea;
        this.onlineUsersList = onlineUsersList;
        new Thread(() -> {
            try {
                privateChatSocket =new ServerSocket(0);
                PortMLR.writePort(UserInfo.getUser_id(),privateChatSocket.getLocalPort());
                privateChatThread =new PrivateChatServer(privateChatSocket,Thread.currentThread(),this);
            } catch (IOException | SQLException | ClassNotFoundException | MyException e) {
                e.printStackTrace();
            }
        }).start();
        sendClientDetails();
        readMessage();
//        System.out.println("Client Joined");
//        System.out.println("Enter the Message:");
//        Scanner scanner=new Scanner(System.in);
//        String message=scanner.nextLine();
//        OutputStream out=socket.getOutputStream();
//        out.write(message.getBytes());
//        System.out.println("Message sent to the server");
//        out.close();
//        scanner.close();
//        socket.close();
    }

    public void sendMessage(String message) throws IOException {
        message = message + "\n";
//        out.write(message.getBytes());
        oos.writeObject(message);

    }

    public void readMessage() throws IOException {
        clientWorker = new ClientWorker(in, textArea, onlineUsersList);
        clientWorker.start();
    }

    public void disconnect() throws IOException, InterruptedException, MyException, SQLException, ClassNotFoundException {
//        in.close();
//        out.close();
        this.clientWorker.interrupt();
        /*        holding further execution until clientWorker thread gets interrupted */
        //noinspection StatementWithEmptyBody
        while (!clientWorker.isInterrupted()) {
        }
        this.clientSocket.close();
        System.out.println("stoopped");
        PortMLR.removePort((UserInfo.getUser_id()));
    }

    private void sendClientDetails() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(UserInfo.getUserInfoObjet(UserInfo.getUser_id()));
        oos.flush();
    }

    public boolean checkStatus(String userid) throws SQLException, ClassNotFoundException, MyException {
        String status=UserDAO.getStatus(new UserDTO(userid));
        if(status!=null)
            return status.equals("N");
        else
            throw new MyException("Sorry! no user with userid - "+userid+"exists till now");
    }

    public void initiatePrivateChat(String userid) throws SQLException, ClassNotFoundException, MyException, IOException {
        this.initiator=true;
        if(checkStatus(userid)){
            throw new MyException("Sorry!, "+userid+" is not online right now.");
        }
        if(!establishConnection(userid)){
            throw new MyException("unknown error occurred.");
        }
        pcout=mySocket.getOutputStream();
        pcout.write((UserInfo.getUser_id()+"\n").getBytes());
        pcout.flush();
        PrivateChatScreen privateChatScreen=new PrivateChatScreen(this, privateChatThread, pcout);
        PCReader=new PrivateChatReader(mySocket,privateChatScreen.getPchattxtarea());
        PCReader.start();
    }
    private boolean establishConnection(String userid) throws IOException, SQLException, ClassNotFoundException, MyException {
        mySocket =new Socket(ConfigReader.getValue("SERVER_IP"), Integer.parseInt(PortMLR.getPort(userid)));
        return mySocket.isConnected();
    }

    public void sendMessageTo(String message) throws IOException {
            message += "\n";
            pcout.write(message.getBytes());
    }
    public void sendMessageTo(String message,OutputStream pcout2) throws IOException {
            message += "\n";
            pcout2.write(message.getBytes());
    }

    public boolean getConfirmation(String name){
        int input=JOptionPane.showConfirmDialog(clientChatScreen,name+" wants to talk to you","Chat with "+name+" ?",JOptionPane.YES_NO_OPTION);
        return input == 0;
    }

    public void setInitiator(boolean initiator) {
        this.initiator = initiator;
    }

    public boolean isInitiator() {
        return initiator;
    }

    public void stopPCReader() throws IOException {
        PCReader.interrupt();
        mySocket.close();
    }
}
