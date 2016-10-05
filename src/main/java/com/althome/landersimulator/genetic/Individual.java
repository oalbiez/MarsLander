package com.althome.landersimulator.genetic;

import com.althome.landersimulator.input.DesiredControlsSequence;
import com.althome.landersimulator.physic.ControlsConstraints;

import java.util.Comparator;

/**
 * Created by Arnaud on 25/09/2016.
 */
public class Individual {

    private DesiredControlsSequence genome;
    private int fitness;

    private Individual() {

    }

    private Individual(DesiredControlsSequence genome, int fitness) {
        this.genome = genome;
        this.fitness = fitness;
    }

    public Individual duplicate() {
        DesiredControlsSequence cloneGenome = this.genome.duplicate();
        int fitness = this.fitness;
        return new Individual(cloneGenome, fitness);
    }

    public DesiredControlsSequence getGenome() {
        return genome;
    }

    public static Individual generateRandom(final int size, final ControlsConstraints cstr) {
        Individual randomInd = new Individual();
        randomInd.genome = DesiredControlsSequence.generateRandom(size,cstr);
        return randomInd;
    }

    public static Comparator<Individual> fitnessComparator = new Comparator<Individual>() {
        @Override
        public int compare(Individual o1, Individual o2) {
            return Integer.compare(o2.fitness, o1.fitness);
        }
    };

}
