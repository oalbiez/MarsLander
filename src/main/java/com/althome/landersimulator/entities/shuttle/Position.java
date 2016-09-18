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
public class Position {
    
    public double x;
    public double y;
    
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString(DecimalFormat decFormat) {
        return "X="+decFormat.format(x)+"m, Y="+decFormat.format(y)+"m";
    }
    
    public Position duplicate() {
        return new Position(x,y);
    }
    
}
