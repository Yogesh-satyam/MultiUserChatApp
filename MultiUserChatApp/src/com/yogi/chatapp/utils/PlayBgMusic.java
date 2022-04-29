/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.utils;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class PlayBgMusic extends Thread{
    private final MP3Player mp3Player;
    public PlayBgMusic(){
        String musicPath = "src/music/bensound-buddy.mp3";
        mp3Player=new MP3Player(new File(musicPath));
    }
    @Override
    public void run() {
        mp3Player.play();
    }

    @Override
    public void interrupt() {
        mp3Player.stop();
        super.interrupt();
    }
}
