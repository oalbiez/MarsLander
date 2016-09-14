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
public class FuelTank {
    
    public int remaining;
    
    public FuelTank(int fuel) {
        this.remaining = fuel;
    }
    
    public FuelTank duplicate() {
        return new FuelTank(remaining);
    }
    
    @Override
    public String toString() {
        return "Fuel="+remaining+"l";
    }
    
}
