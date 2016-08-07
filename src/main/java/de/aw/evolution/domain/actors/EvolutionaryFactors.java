package de.aw.evolution.domain.actors;

/**
 * @author armin.weisser
 */
public class EvolutionaryFactors {

    private final Reproduction reproduction;

    private final GeneticDrift geneticDrift;

    private final Mutation mutation;

    public EvolutionaryFactors(Reproduction reproduction, GeneticDrift geneticDrift, Mutation mutation) {
        this.reproduction = reproduction;
        this.geneticDrift = geneticDrift;
        this.mutation = mutation;
    }

    public Reproduction reproduction() {
        return reproduction;
    }

    public GeneticDrift geneticDrift() {
        return geneticDrift;
    }

    public Mutation mutation() {
        return mutation;
    }
}
