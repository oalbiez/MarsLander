package com.althome.landersimulator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.althome.landersimulator.input.DesiredControlsSequence;
import com.althome.landersimulator.physic.ControlsConstraints;

/**
 *
 * @author Arnaud
 */
public class App {
    public static void main(String args[]) {

        DesiredControlsSequence seq = DesiredControlsSequence.generateRandom(5, new ControlsConstraints());
        System.out.println(seq);

    }

}
