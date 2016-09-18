/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.entities.shuttle;

import java.text.DecimalFormat;

/**
 *
 * @author Arnaud
 */
public class Speed {
    
    public double hSpeed;
    public double vSpeed;
    
    public Speed(double hSpeed, double vSpeed) {
        this.hSpeed = hSpeed;
        this.vSpeed = vSpeed;
    }
    
    public String toString(DecimalFormat decFormat) {
        return "HSpeed="+decFormat.format(hSpeed)+"m/s, VSpeed="+decFormat.format(vSpeed)+"m/s";
    }
    
    public Speed duplicate() {
        return new Speed(hSpeed, vSpeed);
    }  
    
}
