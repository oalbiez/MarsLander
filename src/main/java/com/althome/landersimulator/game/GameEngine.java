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

    public Shuttle computeNextState(final Surface surface, final Shuttle shuttle, final DesiredControls desiredControls, boolean duplicate) {
        Shuttle s = shuttle;
        if ( duplicate ) {
            s = shuttle.duplicate();
        }
        return runSequence(s, surface, desiredControls);
    }

    public Shuttle computeFinalState(final Surface surface, final Shuttle shuttle, final DesiredControlsSequence sequence, boolean duplicate) {
        Shuttle currentShuttle = shuttle;
        for (int i=0; i<sequence.getSequence().size(); i++) {
            final DesiredControls nextAction = sequence.getSequence().get(i);
            currentShuttle = computeNextState(surface, currentShuttle, nextAction, duplicate);
        }
        return currentShuttle;
    }

    private Shuttle runSequence(Shuttle shuttle, Surface surface, DesiredControls desiredControls) {
        if ( shuttle.status == Status.CRASHED || shuttle.status == Status.LANDED ) return shuttle;
        shuttle.control = this.physicEngine.computeControl(shuttle, desiredControls);
        shuttle.fuel = this.physicEngine.computeFuel(shuttle);
        shuttle.position = this.physicEngine.computePosition(shuttle);
        shuttle.speed = this.physicEngine.computeSpeed(shuttle);
        shuttle.status = this.physicEngine.computeStatus(shuttle, surface);
        return shuttle;
    }
    
    
}
