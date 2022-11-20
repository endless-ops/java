package com.dc18669.beating.heart.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static final int COUNT = 2000;
    // Π
    private static final double PI = Math.PI;
    // 爱心的默认大小
    private static final int DEFAULT_SIZE = 10;
    // 默认颜色
    private final Color color = new Color(231, 124, 142);

    // 画布中心
    private int centerX;
    private int centerY;

    private int rFrame = 0;

    public DrawHeart() {

    }


    @Override
    public void paint(Graphics g) {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        g.setColor(color);
        Heart heart = new Heart();
        draw(g, heart, rFrame);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(160);
            rFrame = rFrame + 1;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }

    private void draw(Graphics g, Heart heart, int renderFrame) {
        heart.render(g, renderFrame);
    }

    /**
     * -计算心形函数的坐标点
     *
     * @param t           角度
     * @param shrinkRatio 显示大小（收缩率）
     * @return 返回坐标点
     */
    private HeartPoint getHeartPoints(double t, double shrinkRatio) {
        // 若显示大小是0，则为默认值
        if (shrinkRatio == 0.0) {
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
     * @param beta beta
     * @return 返回新坐标点
     */
    private HeartPoint getScatterPoints(int x, int y, double beta) {
        x = x - (int) (-beta * Math.log(Math.random()) * (x - centerX));
        y = y - (int) (-beta * Math.log(Math.random()) * (y - centerY));
        return new HeartPoint(x, y, 0);
    }

    /**
     * -跳动
     *
     * @param x     横向坐标
     * @param y     纵向坐标
     * @param ratio 比例
     * @return 返回新坐标点
     */
    private HeartPoint getShirkPoints(int x, int y, double ratio) {
        double force = -1 / (Math.pow(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2), 0.6));
        double dx = ratio * force * (x - centerX);
        double dy = ratio * force * (y - centerY);

        x -= dx;
        y -= dy;
        return new HeartPoint(x, y, 0);
    }

    /**
     * -自定义曲线函数，调整跳动周期
     *
     * @param p 角度
     * @return 返回纵向值
     */
    private double getCurve(double p) {
        return 2 * (3 * Math.sin(4 * p)) / (2 * PI);
    }

    private class Heart {
        // 原始爱心坐标集合
        private final List<HeartPoint> ohps;
        // 边缘扩散效果点坐标集合
        private final List<HeartPoint> shps;
        // 中心扩散效果点坐标集合
        private final List<HeartPoint> chps;
        // 每帧动态点坐标
        private final List<List<HeartPoint>> alps;

        private final int frame = 20;

        public Heart() {

            ohps = new ArrayList<>();
            shps = new ArrayList<>();
            chps = new ArrayList<>();
            alps = new ArrayList<>();

            build(COUNT);

            for (int i = 0; i < frame; i++) {
                getCalc(frame);
            }
        }

        private void build(int number) {
            Random random = new Random();
            for (int count = 0; count < number; count++) {
                double t = random.nextDouble() * 2 * PI;
                ohps.add(getHeartPoints(t, 0));
            }

            // 爱心内扩散
            for (HeartPoint hp : ohps) {
                for (int i = 0; i < 3; i++) {
                    shps.add(getScatterPoints(hp.getX(), hp.getY(), 0.05));
                }
            }

            // 爱心内再次扩散
            for (int i = 0; i < 4000; i++) {
                HeartPoint hp = ohps.get(random.nextInt(ohps.size()));
                chps.add(getScatterPoints(hp.getX(), hp.getY(), 0.17));
            }
        }

        /**
         * -
         *
         * @param x
         * @param y
         * @param ratio
         * @return
         */
        private HeartPoint getCalcPosition(int x, int y, double ratio) {
            Random random = new Random();
            double force = -1 / (Math.pow(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2), 0.526));
            double dx = ratio * force * (x - centerX) + random.nextInt(2) - 1;
            double dy = ratio * force * (y - centerY) + random.nextInt(2) - 1;

            x -= dx;
            y -= dy;
            return new HeartPoint(x, y, 0);
        }

        private void getCalc(int xFrame) {
            List<HeartPoint> alpsB = new ArrayList<>();
            //圆滑的周期的缩放比例
            double ratio = 10 * getCurve(xFrame * 1.0 / 10 * PI);

            double haloRadius = 4 + 6 * (1 + getCurve(xFrame * 1.0 * PI));
            double haloNumber = 3000 + 4000 * Math.abs(Math.pow(getCurve(xFrame * 1.0 * PI), 2));

            // 光环
            //光环的点坐标集合
            List<HeartPoint> ahp = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < haloNumber; i++) {
                HeartPoint hp = getHeartPoints(random.nextDouble() * 2 * PI, 11.6);
                hp = getShirkPoints(hp.getX(), hp.getY(), haloRadius);


                if (!ahp.contains(hp)) {
                    ahp.add(hp);
                    int x = hp.getX() + random.nextInt(28) - 14;
                    int y = hp.getY() + random.nextInt(28) - 14;

                    int[] a = new int[]{1, 2, 2};
                    int size = a[random.nextInt(3)];
                    alpsB.add(new HeartPoint(x, y, size));
                }
            }

            // 轮廓
            for (HeartPoint hp : ohps) {
                HeartPoint cp = getCalcPosition(hp.getX(), hp.getY(), ratio);
                int size = random.nextInt(3) + 1;
                alpsB.add(new HeartPoint(cp.getX(), cp.getY(), size));
            }

            // 内容
            for (HeartPoint hp : shps) {
                HeartPoint cp = getCalcPosition(hp.getX(), hp.getY(), ratio);
                int size = random.nextInt(2) + 1;
                alpsB.add(new HeartPoint(cp.getX(), cp.getY(), size));
            }

            for (HeartPoint hp : chps) {
                HeartPoint cp = getCalcPosition(hp.getX(), hp.getY(), ratio);
                int size = random.nextInt(2) + 1;
                alpsB.add(new HeartPoint(cp.getX(), cp.getY(), size));
            }

            alps.add(alpsB);
        }

        private void render(Graphics g, int renderFrame) {
            int index = renderFrame % frame;
            List<HeartPoint> hps = alps.get(index);
            for (HeartPoint hp : hps) {
                g.fillOval(hp.getX(), hp.getY(), hp.getSize(), hp.getSize());
            }
        }
    }
}


