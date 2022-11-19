package com.dc18669.love.dynamics;

import java.util.Random;

public class HeartUtil {

    private HeartPoint heartPoint;

    // 爱心位置横坐标
    private int posX;

    // 爱心位置纵坐标
    private int posY;

    // 大小
    private int size;

    public HeartUtil(int size, int posX, int posY) {
        if (size == 0) {
            this.size = 11;
        } else {
            this.size = size;
        }
        this.posX = posX;
        this.posY = posY;
    }

    public HeartPoint setHeartFunc(double xc) {
        heartPoint = new HeartPoint();
        double distXB = 16 * Math.pow(Math.sin(xc), 3);
        double distYB = -(13 * Math.cos(xc) - 5 * Math.cos(2 * xc) - 2 * Math.cos(3 * xc) - Math.cos(4 * xc));

        // 原式所画的心形图案较小，需要放大
        distXB *= size;
        distYB *= size;

        // 设置心形图案在画布中的位置
        distXB += posX;
        distYB += posY;

        heartPoint.setDistX((int) distXB);
        heartPoint.setDistY((int) distYB);
        return heartPoint;
    }

    /**
     * -数据扩散
     *
     * @param dc 扩散系数
     */
    public HeartPoint scattered(double dc, int distX, int distY) {

//        double nX = dc * Math.sin(Math.random() * Math.PI + (Math.PI / 2));
//        double nY = dc * Math.sin(Math.random() * Math.PI + (Math.PI / 2));

//        double nX = -dc * Math.sin(Math.random() * Math.PI + (Math.PI / 2));
//        double nY = -dc * Math.sin(Math.random() * Math.PI + (Math.PI / 2));

        double nX = -dc * Math.log(Math.random());
        double nY = -dc * Math.log(Math.random());

        int dx = (int) ((distX - posX) * nX);
        int dy = (int) ((distY - posY) * nY);

        heartPoint.setDistX(distX - dx);
        heartPoint.setDistY(distY - dy);
        return heartPoint;
    }


    /**
     * -自定义曲线函数，调整跳动周期
     *
     * @param p
     * @return
     */
    public double curve(double p) {
        return 2 * (3 * Math.sin(4 * p)) / (2 * Math.PI);
    }

    /**
     * -抖动
     *
     * @param distX
     * @param distY
     * @param sc
     * @return
     */
    public HeartPoint shake(int distX, int distY, double sc) {
        double force = -1 / Math.pow((Math.pow((distX - posX), 2) + Math.pow((distY - posY), 2)), 0.6);
        double dx = sc * force * (distX - posX);
        double dy = sc * force * (distY - posY);

        heartPoint.setDistX((int) (distX - dx));
        heartPoint.setDistY((int) (distY - dy));
        return heartPoint;

    }

    /**
     * -调整缩放比例
     *
     * @return
     */
    public HeartPoint getPosition(int distX, int distY, double pos) {
        Random random = new Random();
        double force = -1 / Math.pow((Math.pow((distX - posX), 2) + Math.pow((distY - posY), 2)), 0.520);
        double dx = pos * force * (distX - posX) + random.nextInt(2) * 2 - 1;
        double dy = pos * force * (distY - posY) + random.nextInt(2) * 2 - 1;

        heartPoint.setDistX((int) (distX - dx));
        heartPoint.setDistY((int) (distY - dy));
        return heartPoint;
    }

    public void set(double dc) {

        double ratio = 10 * curve(dc / 10 * Math.PI);
        int haloRadius = (int) (4 + 6 * (1 + curve(dc / 10 * Math.PI)));
        int haloNumber = (int) (3000 + 4000 * Math.abs(Math.pow(curve(dc / 10 * Math.PI), 2)));

        Random random = new Random();
        for (int i = 0; i < haloNumber; i++) {
            double t = random.nextDouble() * 2 * Math.PI;
            HeartPoint hp = setHeartFunc(t);
            heartPoint = shake(hp.getDistX(),hp.getDistY(),haloRadius);
        }

    }

    public void render () {

    }


}
