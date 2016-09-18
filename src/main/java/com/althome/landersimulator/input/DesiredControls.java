package com.althome.landersimulator.input;

/**
 * Created by Arnaud on 18/09/2016.
 */
public class DesiredControls {

    public final int thruster;

    public final int tilt;

    public DesiredControls(int thruster, int tilt) {
        this.thruster = thruster;
        this.tilt = tilt;
    }

    @Override
    public String toString() {
        return "desired angle="+tilt+"°, desired power="+thruster+" ("+thruster+"m/s²)";
    }

    public DesiredControls duplicate() {
        return new DesiredControls(thruster, tilt);
    }


}
