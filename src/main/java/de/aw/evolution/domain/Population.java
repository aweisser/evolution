package de.aw.evolution.domain;

import de.aw.evolution.domain.actors.GeneticDrift;
import de.aw.evolution.domain.actors.Mutation;
import de.aw.evolution.domain.actors.EvolutionaryFactorsForReproduction;
import de.aw.evolution.util.FitnessComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

    public void apply(Environment environment) {
        // TODO let the environmental factors affect the fitness of the population
    }

    public void apply(GeneticDrift geneticDrift) {
        // TODO let a GeneticDrift affect the GenPool of the population
    }

    public void apply(Mutation mutation) {
        // TODO let a Mutation affect the GenPool of the population
    }

    public Generation reproduce(EvolutionaryFactorsForReproduction evolutionaryFactorsForReproduction) {
        currentGeneration = currentGeneration.createNexGeneration(evolutionaryFactorsForReproduction);
        return currentGeneration;
    }

    public Collection<Organism> getWeakest(double percentage) {
        List<Organism> weakestFirstList = new ArrayList<>(getIndividuals());
        weakestFirstList.sort(new FitnessComparator().reversed());
        Long offset = Math.round(weakestFirstList.size() * percentage);
        return weakestFirstList.subList(0, offset.intValue());
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

    public void killAll(Collection<Organism> organisms) {
        currentGeneration.removeAll(organisms);
        // TODO iterate all other generations...
    }
}
