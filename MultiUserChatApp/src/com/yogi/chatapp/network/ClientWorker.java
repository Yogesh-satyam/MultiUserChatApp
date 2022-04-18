package com.yogi.chatapp.network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Client Data Read
public class ClientWorker extends Thread {
    private final InputStream in;
    private final JTextArea textArea;

    public ClientWorker(InputStream in, JTextArea textArea) {
        this.in = in;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while (true) {
                line = br.readLine();
                textArea.setText(textArea.getText().replaceAll("(?m)^[ \\t]*\\r?\\n", "") + line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    if (br != null) {
                        br.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
