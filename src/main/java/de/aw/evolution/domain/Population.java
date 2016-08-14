package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.GeneticDrift;
import de.aw.evolution.domain.factors.Mutation;
import de.aw.evolution.domain.factors.Reproduction;

import java.util.HashSet;
import java.util.OptionalDouble;
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
        if(parentGeneration != null) {
            Group dead = death.apply(parentGeneration);
            parentGeneration.removeAll(dead);
            apply(death, parentGeneration.getChildGeneration());
        }
    }

    public Generation apply(Reproduction reproduction) {
        currentGeneration = currentGeneration.createNexGeneration(reproduction);
        return currentGeneration;
    }

    public void apply(Mutation mutation) {
        getIndividuals().forEach( organism -> {
            Genom mutatedGenom = mutation.apply(organism.getGenom());
            organism.setGenom(mutatedGenom);
        });
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

    private static void collectIndividuals(Set<Organism> individuals, Generation parentGeneration) {
        if(parentGeneration == null) {
            return;
        }
        individuals.addAll(parentGeneration);
        collectIndividuals(individuals, parentGeneration.getChildGeneration());
    }

    public Generation getCurrentGeneration() {
        return currentGeneration;
    }

    public Fitness getAverageFitness() {
        if(size() == 0) {
            return new Fitness.EmptyFitness();
        }
        if(getIndividuals().stream().allMatch( o -> o.getFitness().isEmpty())) {
            return new Fitness.EmptyFitness();
        }
        double averageFitness = 0;
        OptionalDouble average = getIndividuals()
                .stream()
                .filter(organism -> !(organism.getFitness() instanceof Fitness.EmptyFitness))
                .mapToDouble(organism -> organism.getFitness().getValue())
                .average();
        if(average.isPresent()) {
            averageFitness = average.getAsDouble();
        }
        return new Fitness(averageFitness);
    }

    @Override
    public String toString() {
        return "Population{" +
                "size=" + size() +
                ", currentGeneration=" + currentGeneration.getLevel() +
                ", averageFitness=" + getAverageFitness() +
                '}';
    }
}
