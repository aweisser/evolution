package de.aw.evolution.domain;

import de.aw.evolution.domain.actors.Reproduction;
import de.aw.evolution.domain.actors.GeneticDrift;
import de.aw.evolution.domain.actors.Mutation;

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

    /**
     * @param startGeneration mandatory. May not be null.
     */
    public Population(Generation startGeneration) {
        if(startGeneration == null) {
            throw new IllegalArgumentException("The first generation may not be null");
        }
        this.currentGeneration = startGeneration;
    }

    /**
     * Applying the environment to the population will kill some individuals.
     * The die rate depends on the fitness of each organism.
     *
     * @param environment
     */
    public void apply(Environment environment) {
        // TODO let the environmental factors affect the fitness of the population
    }

    public void apply(GeneticDrift geneticDrift) {
        // TODO let a GeneticDrift affect the GenPool of the population
    }

    public void apply(Mutation mutation) {
        // TODO let a Mutation affect the GenPool of the population
    }

    public Generation reproduce(Reproduction reproduction) {
        currentGeneration = currentGeneration.createNexGeneration(reproduction);
        return currentGeneration;
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

}
