package com.dc18669.love.dynamics;

import java.awt.*;

/**
 * x = 16 * (sin (t)) ^ 3
 * y = - (13 * cos (t) - 5 * cos(2 * t) - 2 * cos (3 * t) - cos (4 * t))
 * <p>
 * ******************************************************************
 * f(x)=(0.64*sqrt(abs(x))-0.8+1.2^abs(x)*cos(200*x))*sqrt(cos(x))
 * ******************************************************************
 * f(x)=sqrt(1-(abs(x)-1)^2)
 * h(x)=-2*sqrt(1-0.5*abs(x))
 */
public class HeartCanvas extends Canvas implements Runnable {
    // 圆周率
    private static final double PI = Math.PI;

    private final Color color;
    private final int count;
    private int width;
    private int height;

    public HeartCanvas(Color color, int count) {
        this.color = color;
        this.count = count;
    }


    @Override
    public void paint(Graphics g) {
        width = getWidth() / 2;
        height = getHeight() / 2;
        g.setColor(color);

        drawHeart(g);
    }

    @Override
    public void run() {

    }

    private void drawHeart(Graphics g) {
        Heart heart = new Heart();
        HeartDraw heartDraw = new HeartDraw(10, width , height);
        for (int c = 0; c < count; c++) {
            heartDraw.setHeartFunc(Math.random() * 2 * PI, heart);
            g.fillOval(heart.getDistX(), heart.getDistY(), 2, 2);

            // 内扩散
            for (int i = 0; i <= 3; i++) {

                g.fillOval(heart.getDistX(), heart.getDistY(), 2, 2);
            }

            // 外扩散
            for (int i = 0; i <= 4000; i++) {

                g.fillOval(heart.getDistX(), heart.getDistY(), 2, 2);
            }
        }
    }
}
