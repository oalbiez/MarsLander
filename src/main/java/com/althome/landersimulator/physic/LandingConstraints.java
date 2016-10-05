package com.althome.landersimulator.physic;

/**
 * Created by Arnaud on 17/09/2016.
 */
public class LandingConstraints {


    private final double vSpeedLandMax;
    private final double hSpeedLandMax;
    private final int tiltLandMax;


    LandingConstraints() {
        this.vSpeedLandMax = 40;
        this.hSpeedLandMax = 20;
        this.tiltLandMax = 0;
    }

    public LandingConstraints(double vSpeedLandMax, double hSpeedLandMax, int tiltLandMax) {
        this.vSpeedLandMax = vSpeedLandMax;
        this.hSpeedLandMax = hSpeedLandMax;
        this.tiltLandMax = tiltLandMax;
    }

    public double getvSpeedLandMax() {
        return vSpeedLandMax;
    }

    public double gethSpeedLandMax() {
        return hSpeedLandMax;
    }

    int getTiltLandMax() {
        return tiltLandMax;
    }
}
