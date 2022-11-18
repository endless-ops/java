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

    public HeartCanvas(Color color, int count) {
        this.color = color;
        this.count = count;
    }


    @Override
    public void paint(Graphics g) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        g.setColor(color);

        HeartDraw heartDraw = new HeartDraw(width, height, g);
        heartDraw.setCount(count);
        heartDraw.getHeartPoints();

        // 设置循环跳动
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }
}
