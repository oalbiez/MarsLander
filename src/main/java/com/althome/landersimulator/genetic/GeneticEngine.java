package com.althome.landersimulator.genetic;


import com.althome.landersimulator.entities.shuttle.Shuttle;
import com.althome.landersimulator.entities.shuttle.Status;
import com.althome.landersimulator.entities.surface.Surface;
import com.althome.landersimulator.game.GameEngine;
import com.althome.landersimulator.physic.ControlsConstraints;
import com.althome.landersimulator.physic.LandingConstraints;

/**
 * Created by Arnaud on 05/10/2016.
 */
public class GeneticEngine {

    private final GeneticProperties properties;

    private Population population;

    public GeneticEngine(final GeneticProperties properties) {
        this.properties = properties;
    }

    public void generateARandomPopulation(final ControlsConstraints cstr) {
        this.population = new Population(properties.getPopulationSize());
        for (int i=0; i<properties.getPopulationSize(); i++) {
            this.population.addIndividual(Individual.generateRandom(properties.getGenomeSize(),cstr));
        }
    }



    public static int computeFitness(final Individual individual, final GameEngine game, final Surface surface, final Shuttle shuttle, final LandingConstraints landingCstr) {
        Shuttle shuttleClone = shuttle.duplicate();
        shuttleClone = game.computeFinalState(surface, shuttleClone, individual.getGenome(), false);
        int fitness;
        if ( shuttleClone.status == Status.LANDED ) {
            fitness = shuttleClone.fuel.remaining;
        } else if ( shuttleClone.status == Status.FLYING ) {
            fitness = - surface.verticalDistanceFromTheLandingZone(shuttleClone.position)
                      - surface.horizontalDistanceFromMiddleOfTheLandingZone(shuttleClone.position)
            ;
        } else {
            fitness = - surface.verticalDistanceFromTheLandingZone(shuttleClone.position)
                      - surface.horizontalDistanceFromMiddleOfTheLandingZone(shuttleClone.position)
                      - Math.abs((int) shuttleClone.speed.vSpeed) + (int) landingCstr.getvSpeedLandMax()
                      - Math.abs((int)shuttleClone.speed.hSpeed) + (int) landingCstr.gethSpeedLandMax()
                      - Math.abs(shuttleClone.control.tilt)
            ;
        }
        return fitness;
    }

}
