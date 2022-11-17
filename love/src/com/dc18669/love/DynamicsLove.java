package com.dc18669.love;

import javax.swing.*;

/**
 * -跳动的心脏
 */
public class DynamicsLove  extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public DynamicsLove () {
        initWin();
    }


    private void initWin() {
        setTitle("跳动的心脏");
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new DynamicsLove();
    }
}
