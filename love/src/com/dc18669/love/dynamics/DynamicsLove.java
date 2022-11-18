package com.dc18669.love.dynamics;

import javax.swing.*;
import java.awt.*;

/**
 * -跳动的心脏
 */
public class DynamicsLove extends JFrame {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;

    private final HeartCanvas heartCanvas;

    public DynamicsLove(Color color) {
        heartCanvas = new HeartCanvas(color, 2000);
        initWin();
        heart();
    }

    /*
        x^2+y^2+a*x=a*sqrt(x^2+y^2)
        x^2+y^2-a*x=a*sqrt(x^2+y^2）
     */

    /*
        x = 16 * (sin (t)) ^ 3
        y = - (13 * cos (t) - 5 * cos(2 * t) - 2 * cos (3 * t) - cos (4 * t))

     */
    private void initWin() {
        setTitle("跳动的爱");
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void heart() {
        getContentPane().add(heartCanvas);
    }
}
