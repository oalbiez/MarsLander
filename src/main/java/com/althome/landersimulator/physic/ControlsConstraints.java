package com.althome.landersimulator.physic;

/**
 * Created by Arnaud on 17/09/2016.
 */
class ControlsConstraints {

    /* Shuttle controls constraints */
    private final int thrusterStepMax;
    private final int tiltStepMax;
    private final int tiltMax;
    private final int tiltMin;
    private final int thrusterMax;
    private final int thrusterMin;


    ControlsConstraints() {
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

    int getThrusterStepMax() {
        return thrusterStepMax;
    }

    int getTiltStepMax() {
        return tiltStepMax;
    }

    int getTiltMax() {
        return tiltMax;
    }

    int getTiltMin() {
        return tiltMin;
    }

    int getThrusterMax() {
        return thrusterMax;
    }

    int getThrusterMin() {
        return thrusterMin;
    }
}
