package com.anonymousInnerClass;

import java.awt.Toolkit;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        var clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {
    public void start(int interval, boolean beep) {
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
                if(beep) Toolkit.getDefaultToolkit().beep();
            };
        };
        var timer = new Timer(interval, listener);
        timer.start();
    }
}