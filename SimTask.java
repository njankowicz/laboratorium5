package com.company;

import java.util.TimerTask;

public class SimTask extends TimerTask {

    //1
    private SimEngine simEngine;
    //2
    private SpringApplet springApplet;
    //3
    private double deltaT;
    //4
    public SimTask (SimEngine simEngine, SpringApplet springApplet, double deltaT)
    {
    this.simEngine=simEngine;
    this.springApplet=springApplet;
    this.deltaT=deltaT;
    }
    //5
    @Override
    public void run() {
    simEngine.PrzebiegSymulacji(deltaT);
    springApplet.repaint();
    }
}
