/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.entities;

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
        this.position = position.duplicate();
        this.speed = speed.duplicate();
        this.control = control.duplicate();
        this.fuel = fuel.duplicate();
        this.status = status;
    }
    
    public void updateControl(ControlPanel newControl) {

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
        return new Shuttle(position, speed, control, fuel, status);
    }
    
}
