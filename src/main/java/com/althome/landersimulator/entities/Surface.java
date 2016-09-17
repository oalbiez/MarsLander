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
public interface Surface {

    void build(int[] compactedLandX, int[] compactedLandY);
    
    boolean isAFreePosition(final Position position);
    
    boolean isOnTheLandingZone(final Position position);

    String toString();

}
