package com.dc18669.love.dynamics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeartDraw {

    //
    private List<HeartPoint> orginalHeartPoints;
    private List<HeartPoint> scatteredHeartPoints;
    private List<HeartPoint> centerHeartPoints;

    private final int width;
    private final int height;

    private int count;

    private final Graphics graphics;

    public HeartDraw(int width, int height, Graphics graphics) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;

    }

    public void setCount(int count) {
        this.count = count;
    }

    public void getHeartPoints() {
        orginalHeartPoints = new ArrayList<>();
        scatteredHeartPoints = new ArrayList<>();
        centerHeartPoints = new ArrayList<>();
        HeartUtil heartUtil = new HeartUtil(0, width, height);
        Random random = new Random();
        for (int c = 0; c < count; c++) {
            double t = random.nextDouble() * 2 * Math.PI;
            HeartPoint heartPoint = heartUtil.setHeartFunc(t);
            orginalHeartPoints.add(heartPoint);
        }

        for (HeartPoint heartPoint : orginalHeartPoints) {
            for (int i = 0; i < 3; i++) {
                HeartPoint scatteredHeartPoint = heartUtil.scattered(0.05, heartPoint.getDistX(), heartPoint.getDistY());
                scatteredHeartPoints.add(scatteredHeartPoint);
            }
        }

        for (int i = 0; i < 4000; i++) {
            HeartPoint heartPoint = orginalHeartPoints.get(random.nextInt(orginalHeartPoints.size()));
            HeartPoint centerHeartPoint = heartUtil.scattered(0.17, heartPoint.getDistX(), heartPoint.getDistY());
            centerHeartPoints.add(centerHeartPoint);
        }
    }




}
