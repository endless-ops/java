package com.dc18669.love.dynamics;

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


    public double curve(double p) {
        return 2 * (3 * Math.sin(4 * p)) / (2 * Math.PI);
    }

    public void shake(int distX, int distY, double sc) {

    }

    public void getPosition() {

    }


}
