/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.game;

import com.althome.landersimulator.input.DesiredControls;
import com.althome.landersimulator.entities.shuttle.*;
import com.althome.landersimulator.entities.surface.Surface;
import com.althome.landersimulator.input.DesiredControlsSequence;
import com.althome.landersimulator.physic.PhysicEngine;

import java.util.Iterator;


/**
 *
 * @author Arnaud
 */
public class GameEngine {
    
    private final PhysicEngine physicEngine;


    public GameEngine() {
        this.physicEngine = new PhysicEngine();
    }

    public Shuttle computeNextState(final Surface surface, final Shuttle shuttle, DesiredControls desiredControls ) {

        if ( shuttle.status == Status.CRASHED ) return shuttle;

        Shuttle nextShuttle = shuttle.duplicate();
        final ControlPanel nextControl = this.physicEngine.computeControl(nextShuttle, desiredControls);
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


    public Shuttle computeFinalState(final Surface surface, final Shuttle shuttle, final DesiredControlsSequence sequence) {
        Shuttle currentShuttle = shuttle;
        while ( sequence.hasNext() ) {
            final DesiredControls nextAction = sequence.next();
            currentShuttle = computeNextState(surface, currentShuttle, nextAction);
        }
        return currentShuttle;
    }
    
    
}
