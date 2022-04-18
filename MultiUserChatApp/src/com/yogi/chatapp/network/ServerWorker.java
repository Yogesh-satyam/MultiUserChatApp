package com.yogi.chatapp.network;

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread{
    private final Socket clientSocket;
    private final InputStream in;
    private final OutputStream out;
    private final Server server;
    public ServerWorker(Socket clientSocket, Server server) throws IOException {
        this.clientSocket=clientSocket;
        this.server=server;
        in=clientSocket.getInputStream();
        out=clientSocket.getOutputStream();
        System.out.println("New Client Comes");
    }

    @Override
    public void run(){
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while(true){

                line=br.readLine();
                if(line.equalsIgnoreCase("quit")){
                    break;
                }
                //out.write(line.getBytes());  //Client Send
                //Broadcast to all
                for(ServerWorker serverWorker:server.workers){
                    line=line+"\n";
                    serverWorker.out.write(line.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
