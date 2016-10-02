package com.althome.landersimulator.input;

import com.althome.landersimulator.physic.ControlsConstraints;
import com.althome.landersimulator.utils.Utils;

/**
 * Created by Arnaud on 18/09/2016.
 */
public class DesiredControls {

    public int thruster;
    public int tilt;

    public DesiredControls(int thruster, int tilt) {
        this.thruster = thruster;
        this.tilt = tilt;
    }

    public static DesiredControls generateRandom(final ControlsConstraints cstr) {
        return new DesiredControls(Utils.nextInt(cstr.getThrusterMin(), cstr.getThrusterMax()),
                                   Utils.nextInt(cstr.getTiltMin(), cstr.getTiltMax()));
    }

    @Override
    public String toString() {
        return "desired angle="+tilt+"°, desired power="+thruster+" ("+thruster+"m/s²)";
    }

    public DesiredControls duplicate() {
        return new DesiredControls(thruster, tilt);
    }


}
