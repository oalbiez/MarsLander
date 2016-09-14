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
    
    /* Gravity in m/s² */
    private static final double GRAVITY = -3.711;
    
    /* Landing, physical constraints */
    private static final double VSPEED_LAND_MAX = 40;
    private static final double HSPEED_LAND_MAX = 20;
    private static final int TILT_LAND_MAX = 0;

    /*  */
    
    
    public static Status computeStatus(final Shuttle shuttle, final Surface surface) {       
        if ( isInAFreeArea(surface, shuttle) ) {
            return Status.FLYING;
        } else if ( isOnTheLandingZone(surface, shuttle) && respectLandingConstraints(shuttle) ) {
            return Status.LANDED;
        } else {
            return Status.CRASHED;
        }
    }
    
    public static ControlPanel computeControl(Shuttle shuttle) {
        if ( shuttle.fuel.remaining >= shuttle.control.thruster ) {
            return new ControlPanel(shuttle.control.thruster, shuttle.control.tilt);
        } else {
            return new ControlPanel(0, shuttle.control.tilt);
        }
    }
    
    public static FuelTank computeFuel(final Shuttle shuttle) {
        return new FuelTank(shuttle.fuel.remaining - shuttle.control.thruster);
    }
    
    public static Speed computeSpeed(final Shuttle shuttle) {
        return new Speed(
                shuttle.speed.hSpeed - 1 * Math.sin(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster,
                shuttle.speed.vSpeed + 1 * Math.cos(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster + GRAVITY
               );
    }
    
    public static Position computePosition(final Shuttle shuttle) {
        return new Position(
                shuttle.position.x + shuttle.speed.hSpeed - 0.5 * (Math.sin(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster),
                shuttle.position.y + shuttle.speed.vSpeed + 0.5 * (Math.cos(Math.toRadians(shuttle.control.tilt)) * shuttle.control.thruster + GRAVITY)
               );
    }

    private static boolean respectLandingConstraints(final Shuttle shuttle) {
        return Math.abs(shuttle.control.tilt) <= TILT_LAND_MAX
                && Math.abs(shuttle.speed.vSpeed) <= VSPEED_LAND_MAX
                && Math.abs(shuttle.speed.hSpeed) <= HSPEED_LAND_MAX;
    }
    
    public static boolean isInAFreeArea(final Surface surface, final Shuttle shuttle) {
        if ( surface.isAFreePosition(shuttle.position) ) return true;
        else return false;
    }

    public static boolean isOnTheLandingZone(final Surface surface, final Shuttle shuttle) {
        if ( surface.isOnTheLandingZone(shuttle.position) ) return true;
        else return false;
    }

    
    
}
