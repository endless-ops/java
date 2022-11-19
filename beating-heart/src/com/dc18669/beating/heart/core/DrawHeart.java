package com.dc18669.beating.heart.core;

import java.awt.*;
import java.util.List;

/**
 * -实现爱心
 * -实现跳动
 * <p>
 * --函数：
 * <p>
 * * x = 16 * (sin (t)) ^ 3
 * * y = - (13 * cos (t) - 5 * cos(2 * t) - 2 * cos (3 * t) - cos (4 * t))
 * * <p>
 * * ******************************************************************
 * * f(x)=(0.64*sqrt(abs(x))-0.8+1.2^abs(x)*cos(200*x))*sqrt(cos(x))
 * * ******************************************************************
 * * f(x)=sqrt(1-(abs(x)-1)^2)
 * * h(x)=-2*sqrt(1-0.5*abs(x))
 */
public class DrawHeart extends Canvas implements Runnable {
    // Π
    private static final double PI = Math.PI;
    // 爱心的默认大小
    private static final int DEFAULT_SIZE = 10;
    // 默认颜色
    private Color color = new Color(231, 124, 142);

    private List<HeartPoint> ohps;
    private List<HeartPoint> shps;
    private List<HeartPoint> chps;


    // 画布中心
    private int centerX;
    private int centerY;


    @Override
    public void paint(Graphics g) {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;


    }

    @Override
    public void run() {

    }

    /**
     * -计算心形函数的坐标点
     *
     * @param t           角度
     * @param shrinkRatio 显示大小（收缩率）
     * @return 返回坐标点
     */
    private HeartPoint getHeartPoints(double t, int shrinkRatio) {
        // 若显示大小是0，则为默认值
        if (shrinkRatio == 0) {
            shrinkRatio = DEFAULT_SIZE;
        }

        double distX = 16 * Math.pow(Math.sin(t), 3);
        double distY = -(13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t));

        distX *= shrinkRatio;
        distY *= shrinkRatio;

        distX += centerX;
        distY += centerY;
        return new HeartPoint((int) distX, (int) distY, 0);
    }

    /**
     * -散落在内部的点
     *
     * @param x    横向坐标
     * @param y    纵向坐标
     * @param beta 密集度
     * @return 返回新坐标点
     */
    private HeartPoint getScatterPoints(int x, int y, double beta) {

        return new HeartPoint(x, y, 0);
    }
}
