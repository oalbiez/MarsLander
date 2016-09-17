/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.physic;

import com.althome.landersimulator.entities.ControlPanel;
import com.althome.landersimulator.entities.Surface;
import com.althome.landersimulator.entities.Speed;
import com.althome.landersimulator.entities.Position;
import com.althome.landersimulator.entities.Shuttle;
import com.althome.landersimulator.entities.Status;
import com.althome.landersimulator.entities.FuelTank;

/**
 *
 * @author Arnaud
 */
public class PhysicEngine {
    
    private final PhysicProperties physicProperties = new PhysicProperties();

    private final LandingConstraints landCstr = new LandingConstraints();

    private final ControlsConstraints controlsCstr = new ControlsConstraints();


    public PhysicEngine() {

    }

    public Status computeStatus(final Shuttle shuttle, final Surface surface) {
        if ( isInAFreeArea(surface, shuttle) ) {
            return Status.FLYING;
        } else if ( isOnTheLandingZone(surface, shuttle) && respectLandingConstraints(shuttle) ) {
            return Status.LANDED;
        } else {
            return Status.CRASHED;
        }
    }
    
    public ControlPanel computeControl(final Shuttle shuttle, final ControlPanel newInputControl) {
        ControlPanel nextControl = shuttle.control.duplicate();
        if ( newInputControl.thruster > shuttle.control.thruster ) { // increase
            nextControl.thruster = Math.min(newInputControl.thruster, controlsCstr.getThrusterMax());
            nextControl.thruster = Math.min(nextControl.thruster, shuttle.control.thruster + controlsCstr.getThrusterStepMax());
        } else if ( newInputControl.thruster < shuttle.control.thruster ) { // decrease
            nextControl.thruster = Math.max(newInputControl.thruster, controlsCstr.getThrusterMin());
            nextControl.thruster = Math.max(nextControl.thruster, shuttle.control.thruster - controlsCstr.getThrusterStepMax());
        }
        if ( newInputControl.tilt > shuttle.control.tilt ) { // increase
            nextControl.tilt = Math.min(newInputControl.tilt, controlsCstr.getTiltMax());
            nextControl.tilt = Math.min(nextControl.tilt, shuttle.control.tilt + controlsCstr.getTiltStepMax());
        } else if ( newInputControl.tilt < shuttle.control.tilt ) { // decrease
            nextControl.tilt = Math.max(newInputControl.tilt, controlsCstr.getTiltMin());
            nextControl.tilt = Math.max(nextControl.tilt, shuttle.control.tilt - controlsCstr.getTiltStepMax());
        }
        return nextControl;
    }
    
    public FuelTank computeFuel(final Shuttle shuttle) {
        return new FuelTank(shuttle.fuel.remaining - shuttle.control.thruster);
    }
    
    public Speed computeSpeed(final Shuttle shuttle) {
        return new Speed(
                shuttle.speed.hSpeed - 1 * Math.sin(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster,
                shuttle.speed.vSpeed + 1 * Math.cos(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster + physicProperties.getGravity()
               );
    }
    
    public Position computePosition(final Shuttle shuttle) {
        return new Position(
                shuttle.position.x + shuttle.speed.hSpeed - 0.5 * (Math.sin(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster),
                shuttle.position.y + shuttle.speed.vSpeed + 0.5 * (Math.cos(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster + physicProperties.getGravity())
               );
    }

    private boolean respectLandingConstraints(final Shuttle shuttle) {
        return Math.abs(shuttle.control.tilt) <= landCstr.getTiltLandMax()
                && Math.abs(shuttle.speed.vSpeed) <= landCstr.getvSpeedLandMax()
                && Math.abs(shuttle.speed.hSpeed) <= landCstr.gethSpeedLandMax();
    }
    
    private boolean isInAFreeArea(final Surface surface, final Shuttle shuttle) {
        return ( surface.isAFreePosition(shuttle.position) );
    }

    private boolean isOnTheLandingZone(final Surface surface, final Shuttle shuttle) {
        return ( surface.isOnTheLandingZone(shuttle.position) );
    }

    
    
}
