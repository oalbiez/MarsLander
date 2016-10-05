package com.althome.landersimulator.genetic;

/**
 * Created by Arnaud on 05/10/2016.
 */
public class GeneticProperties {


    private final int genomeSize;
    private final int populationSize;
    private final int populationSelected;
    private final int iterations;


    public GeneticProperties() {
        this.genomeSize = 30;
        this.populationSize = 50;
        this.populationSelected = 20;
        this.iterations = 1000;
    }

    public GeneticProperties(int genomeSize, int populationSize, int populationSelected, int iterations) {
        this.genomeSize = genomeSize;
        this.populationSize = populationSize;
        this.populationSelected = populationSelected;
        this.iterations = iterations;
    }

    public int getGenomeSize() {
        return genomeSize;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getPopulationSelected() {
        return populationSelected;
    }

    public int getIterations() {
        return iterations;
    }
}
