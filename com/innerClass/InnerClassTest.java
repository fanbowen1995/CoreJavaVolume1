package com.innerClass;

import java.awt.Toolkit;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.*;

public class InnerClassTest {
    public static void main(String[] args) {
        var clock = new TalkingClock(1000, true);
        //clock.new TimePrinter();
        clock.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        var listener = new TimePrinter();
        var timer = new Timer(interval, listener);
        timer.start();
    }

    public class TimePrinter implements ActionListener {
        // public static int fun() {//内部类不能有static方法
        //     //System.out.println("static function.");
        //     return 0;
        // }
        public void actionPerformed(ActionEvent event) {
            System.out.println("At this tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}