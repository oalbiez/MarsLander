package com.althome.landersimulator.physic;

/**
 * Created by Arnaud on 17/09/2016.
 */
public class ControlsConstraints {

    /* Shuttle controls constraints */
    private final int thrusterStepMax;
    private final int tiltStepMax;
    private final int tiltMax;
    private final int tiltMin;
    private final int thrusterMax;
    private final int thrusterMin;


    public ControlsConstraints() {
        this.thrusterStepMax = 1;
        this.tiltStepMax = 15;
        this.tiltMax = 90;
        this.tiltMin = -90;
        this.thrusterMax = 4;
        this.thrusterMin = 0;
    }

    public ControlsConstraints(int thrusterStepMax, int tiltStepMax, int tiltMax, int tiltMin, int thrusterMax, int thrusterMin) {
        this.thrusterStepMax = thrusterStepMax;
        this.tiltStepMax = tiltStepMax;
        this.tiltMax = tiltMax;
        this.tiltMin = tiltMin;
        this.thrusterMax = thrusterMax;
        this.thrusterMin = thrusterMin;
    }

    public int getThrusterStepMax() {
        return thrusterStepMax;
    }

    public int getTiltStepMax() {
        return tiltStepMax;
    }

    public int getTiltMax() {
        return tiltMax;
    }

    public int getTiltMin() {
        return tiltMin;
    }

    public int getThrusterMax() {
        return thrusterMax;
    }

    public int getThrusterMin() {
        return thrusterMin;
    }
}
