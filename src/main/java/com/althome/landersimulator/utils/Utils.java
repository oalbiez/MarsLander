/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Arnaud
 */
public class Utils {
    
    public static final DecimalFormat FMT_3_DEC = new DecimalFormat("#0.000");

    public static final DecimalFormat FMT_0_DEC = new DecimalFormat("#0");

    public static final Random rnd = new Random(66);

    public static int nextInt(int min, int max) {
        return rnd.nextInt(max-min+1) + min ;
    }

    public static int nextInt(int min, int max, int FACTOR) {
        return (rnd.nextInt(max-min+1) + min) * FACTOR;
    }


}
