package com.althome.landersimulator.physic;

/**
 * Created by Arnaud on 17/09/2016.
 */
public class PhysicProperties {

    /* Gravity in m/s² */
    private final double gravity;


    public PhysicProperties() {
        this.gravity = -3.711;
    }

    public PhysicProperties(double gravity) {
        this.gravity = gravity;
    }

    public double getGravity() {
        return this.gravity;
    }

}
