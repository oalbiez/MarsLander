package com.althome.landersimulator.genetic;

import java.util.ArrayList;

/**
 * Created by Arnaud on 05/10/2016.
 */
public class Population {

    private ArrayList<Individual> individuals;

    public Population(int size) {
        this.individuals = new ArrayList<>(size);
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void addIndividual(Individual individual) {
        this.individuals.add(individual);
    }
}
