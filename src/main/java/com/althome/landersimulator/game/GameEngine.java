/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.*;
import com.althome.landersimulator.physic.PhysicEngine;


/**
 *
 * @author Arnaud
 */
public class GameEngine {
    
    private final PhysicEngine physicEngine;


    public GameEngine() {
        this.physicEngine = new PhysicEngine();
    }

    public Shuttle computeNextState(final Surface surface, final Shuttle shuttle, ControlPanel newControl ) {

        if ( shuttle.status == Status.CRASHED ) return shuttle;

        Shuttle nextShuttle = shuttle.duplicate();
        final ControlPanel nextControl = this.physicEngine.computeControl(nextShuttle, newControl);
        nextShuttle.control = nextControl;
        final FuelTank nextRemainingFuel = this.physicEngine.computeFuel(nextShuttle);
        nextShuttle.fuel = nextRemainingFuel;
        final Position nextPosition = this.physicEngine.computePosition(nextShuttle);
        nextShuttle.position = nextPosition;
        final Speed nextSpeed = this.physicEngine.computeSpeed(nextShuttle);
        nextShuttle.speed = nextSpeed;
        final Status nextStatus = this.physicEngine.computeStatus(nextShuttle, surface);
        nextShuttle.status = nextStatus;

        return nextShuttle;
    }
    
    
    
}
