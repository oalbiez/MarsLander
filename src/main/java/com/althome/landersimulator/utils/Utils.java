/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.landersimulator.utils;

import java.text.DecimalFormat;

/**
 *
 * @author Arnaud
 */
public class Utils {
    
    public static final DecimalFormat FMT_3_DEC = new DecimalFormat("#0.000");

    public static final DecimalFormat FMT_0_DEC = new DecimalFormat("#0");

    public static final Double precision = new Double(0.001d);


    public static final boolean areEquals(Double v1, Double v2) {
        return Math.abs(v1 - v2)  < precision;
    }

}
