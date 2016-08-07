package de.aw.evolution.domain.actors;

/**
 * @author armin.weisser
 */
public class EvolutionaryFactors {

    private final EvolutionaryFactorsForReproduction evolutionaryFactorsForReproduction;

    private final GeneticDrift geneticDrift;

    private final Mutation mutation;

    public EvolutionaryFactors(EvolutionaryFactorsForReproduction evolutionaryFactorsForReproduction, GeneticDrift geneticDrift, Mutation mutation) {
        this.evolutionaryFactorsForReproduction = evolutionaryFactorsForReproduction;
        this.geneticDrift = geneticDrift;
        this.mutation = mutation;
    }

    public EvolutionaryFactorsForReproduction getEvolutionaryFactorsForReproduction() {
        return evolutionaryFactorsForReproduction;
    }

    public GeneticDrift getGeneticDrift() {
        return geneticDrift;
    }

    public Mutation getMutation() {
        return mutation;
    }
}
