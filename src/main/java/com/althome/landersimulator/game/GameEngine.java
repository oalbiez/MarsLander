/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.*;


import static com.althome.landersimulator.physic.PhysicEngine.*;

/**
 *
 * @author Arnaud
 */
public class GameEngine {
    
    
    public static Shuttle computeNextState(final Surface surface, final Shuttle shuttle, ControlPanel newControl ) {
        
        final ControlPanel nextControl = computeControl(shuttle);
        final FuelTank nextRemainingFuel = computeFuel(shuttle);
        final Position nextPosition = computePosition(shuttle);
        final Speed nextSpeed = computeSpeed(shuttle);
        final Status nextStatus = computeStatus(shuttle, surface);
        
        final Shuttle nextShuttle = new Shuttle(nextPosition, nextSpeed, nextControl, nextRemainingFuel, nextStatus);
        return nextShuttle;
    }
    
    
    
}
