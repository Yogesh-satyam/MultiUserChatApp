/*
 * Created by JFormDesigner on Mon Apr 18 21:13:01 IST 2022
 */

package com.yogi.chatapp.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yogesh Satyam
 */
public class SplashScreen extends JWindow {
    private int count = 0;

    private Timer timer;

    public SplashScreen() {
        initComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        SplashScreen startScreen = new SplashScreen();
        startScreen.runProgressBar();

    }

    private void runProgressBar() {
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar1.setValue(count);
                count++;
                if (count > 100) {
                    if (timer != null)
                        timer.stop();
                    SplashScreen.this.setVisible(false);
                    SplashScreen.this.dispose();
                    UserScreen userScreen = new UserScreen();
                }
            }
        });
        timer.start();
    }

    private void progressBar1StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yogesh Satyam
        progressBar1 = new JProgressBar();
        label1 = new JLabel();

        //======== this ========
        setForeground(new Color(0, 134, 155));
        setBackground(SystemColor.activeCaption);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- progressBar1 ----
        progressBar1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        progressBar1.setBackground(new Color(204, 255, 204));
        progressBar1.setForeground(new Color(0, 143, 216));
        progressBar1.setStringPainted(true);
        progressBar1.setBorder(null);
        progressBar1.addChangeListener(e -> {
            progressBar1StateChanged(e);
            progressBar1StateChanged(e);
        });
        contentPane.add(progressBar1);
        progressBar1.setBounds(45, 325, 500, 39);

        //---- label1 ----
        label1.setText("text");
        label1.setIcon(new ImageIcon(getClass().getResource("/images/Chat.png")));
        contentPane.add(label1);
        label1.setBounds(0, 0, 600, 380);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(600, 380);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yogesh Satyam
    private JProgressBar progressBar1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
