package com.dc18669.love.dynamics;

public class HeartDraw {

    // 爱心位置横坐标
    private int posX;

    // 爱心位置纵坐标
    private int posY;

    // 大小
    private int size;

    public HeartDraw(int size, int posX, int posY) {
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }

    public void setHeartFunc(double xc, Heart heart) {
        double distXB = 16 * Math.pow(Math.sin(xc), 3);
        double distYB = -(13 * Math.cos(xc) - 5 * Math.cos(2 * xc) - 2 * Math.cos(3 * xc) - Math.cos(4 * xc));

        // 原式所画的心形图案较小，需要放大
        distXB *= size;
        distYB *= size;

        // 设置心形图案在画布中的位置
        distXB += posX;
        distYB += posY;

        heart.setDistX((int) distXB);
        heart.setDistY((int) distYB);
    }

    /**
     * -数据扩散
     *
     * @param dc 扩散系数
     */
    public void scattered(double dc) {

    }
}
