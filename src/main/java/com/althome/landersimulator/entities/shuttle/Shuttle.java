/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.entities.shuttle;

import static com.althome.landersimulator.utils.Utils.FMT_0_DEC;

/**
 *
 * @author Arnaud
 */
public class Shuttle {
    
    public Position position;
    public Speed speed;
    public ControlPanel control;
    public FuelTank fuel;
    public Status status;
    
    public Shuttle(Position position, Speed speed, ControlPanel control, FuelTank fuel, Status status) {
        this.position = position;
        this.speed = speed;
        this.control = control;
        this.fuel = fuel;
        this.status = status;
    }

    
    @Override
    public String toString() {
        return position.toString(FMT_0_DEC) + " " 
                + speed.toString(FMT_0_DEC) + " " 
                + fuel + " " 
                + control 
                + " : " + status;
               
    }
    
    public Shuttle duplicate() {
        return new Shuttle(position.duplicate(), speed.duplicate(), control.duplicate(), fuel.duplicate(), status);
    }
    
}
