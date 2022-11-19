package com.dc18669.beating.heart.core;

import javax.swing.*;
import java.awt.*;

public class ShowHeart extends JFrame {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;

    public ShowHeart() {
        setWin();
        showHeart();
    }

    private void setWin() {
        setTitle("跳动的爱");
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void showHeart() {
        getContentPane().add(new DrawHeart());
    }
}
