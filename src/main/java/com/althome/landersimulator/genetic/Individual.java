package com.althome.landersimulator.genetic;

import com.althome.landersimulator.input.DesiredControlsSequence;

/**
 * Created by Arnaud on 25/09/2016.
 */
public class Individual {

    private DesiredControlsSequence genome;
    private int fitness;

    private Individual(DesiredControlsSequence genome, int fitness) {
        this.genome = genome;
        this.fitness = fitness;
    }

    public Individual duplicate() {
        DesiredControlsSequence cloneGenome = this.genome.duplicate();
        int fitness = this.fitness;
        return new Individual(genome, fitness);
    }

}
