/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.entities;

/**
 *
 * @author Arnaud
 */
public class ControlPanel {
    
    public int thruster;
    public int tilt;
    
    public ControlPanel(int thruster, int tilt) {
        this.thruster = thruster;
        this.tilt = tilt;
    }
    
    @Override
    public String toString() {
        return "angle="+tilt+"°, power="+thruster+" ("+thruster+"m/s²)";
    }

    public ControlPanel duplicate() {
        return new ControlPanel(thruster, tilt);
    }    
    
}
