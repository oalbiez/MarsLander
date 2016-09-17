package com.althome.landersimulator.physic;

/**
 * Created by Arnaud on 17/09/2016.
 */
class PhysicProperties {

    /* Gravity in m/s² */
    private final double gravity;


    PhysicProperties() {
        this.gravity = -3.711;
    }

    double getGravity() {
        return this.gravity;
    }

}
