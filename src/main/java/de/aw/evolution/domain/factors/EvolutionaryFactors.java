package de.aw.evolution.domain.factors;

/**
 * @author armin.weisser
 */
public class EvolutionaryFactors {

    private final Reproduction reproduction;

    private final GeneticDrift geneticDrift;

    private final Mutation mutation;

    private final Death death;

    public EvolutionaryFactors(Reproduction reproduction, GeneticDrift geneticDrift, Mutation mutation, Death death) {
        this.reproduction = reproduction;
        this.geneticDrift = geneticDrift;
        this.mutation = mutation;
        this.death = death;
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

    public Death death() {
        return death;
    }
}
