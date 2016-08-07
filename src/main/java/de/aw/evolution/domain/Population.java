package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.GeneticDrift;
import de.aw.evolution.domain.factors.Mutation;
import de.aw.evolution.domain.factors.Reproduction;

import java.util.HashSet;
import java.util.Set;

/**
 * Eine Population ist eine Gruppe von Individuen der gleichen Art, die aufgrund ihrer Entstehungsprozesse
 * miteinander verbunden sind, eine Fortpflanzungsgemeinschaft bilden und zur gleichen Zeit
 * in einem einheitlichen Areal zu finden sind.
 *
 * Die Population ist über das Environment den gleichen Umwelteinflüssen ausgesetzt.
 *
 * @author armin.weisser
 */
public class Population  {

    private Generation currentGeneration;

    public Population() {
        this(null);
    }
    /**
     * @param startGeneration - if null a new, empty Generation will be created.
     */
    public Population(Generation startGeneration) {
        if(startGeneration == null) {
            startGeneration = Generation.createFirstGeneration();
        }
        this.currentGeneration = startGeneration;
    }

    /**
     * Applying the environment to the population will define the fitness of each individual.
     *
     * @param environment
     */
    public void apply(Environment environment) {
        getIndividuals().forEach(organism -> {
            Fitness fitness = environment.apply(organism);
            organism.setFitness(fitness);
        });
    }

    /**
     * Applying Death to the population will propably erase some of the individuals
     *
     * @param death
     */
    public void apply(Death death) {
        apply(death, oldestGeneration(this.currentGeneration));
    }

    private void apply(Death death, Generation parentGeneration) {
        death.apply(parentGeneration);
        apply(death, parentGeneration.getChildGeneration());
    }

    public Generation apply(Reproduction reproduction) {
        currentGeneration = currentGeneration.createNexGeneration(reproduction);
        return currentGeneration;
    }

    public void apply(Mutation mutation) {
        getIndividuals().forEach( organism -> mutation.apply(organism.getGenom()));
    }

    public void apply(GeneticDrift geneticDrift) {
        geneticDrift.apply(this);
    }

    public GenePool getGenePool() {
        return GenePool.create(getIndividuals());
    }

    public int size() {
        return getIndividuals().size();
    }

    public Set<Organism> getIndividuals() {
        Set<Organism> individuals = new HashSet<>();
        collectIndividuals(individuals, oldestGeneration(currentGeneration));
        return individuals;
    }

    private Generation oldestGeneration(Generation generation) {
        if(generation.getParentGeneration() == null) {
            return generation;
        }
        return oldestGeneration(generation.getParentGeneration());
    }

    private static void collectIndividuals(Set<Organism> individuals, Generation oldestGeneration) {
        if(oldestGeneration == null) {
            return;
        }
        individuals.addAll(oldestGeneration);
        collectIndividuals(individuals, oldestGeneration.getChildGeneration());
    }

    public Generation getCurrentGeneration() {
        return currentGeneration;
    }
}
