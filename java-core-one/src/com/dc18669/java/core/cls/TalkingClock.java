package com.dc18669.java.core.cls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock (int interval, boolean beep) {
        this.beep = beep;
        this.interval = interval;
    }

    public void start() {
        TimePrinter listener = new TimePrinter();
        Timer timer = new Timer(interval,listener);
        timer.start();
    }

    public class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
            if (beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
