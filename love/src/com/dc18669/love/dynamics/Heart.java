package com.dc18669.love.dynamics;

/**
 * -爱心实体
 */
public class Heart {
    // 爱心横向坐标
    private int distX;
    // 爱心纵向坐标
    private int distY;

    public Heart() {
    }

    public void setDistX(int distX) {
        this.distX = distX;
    }

    public void setDistY(int distY) {
        this.distY = distY;
    }

    public int getDistX() {
        return distX;
    }

    public int getDistY() {
        return distY;
    }
}
