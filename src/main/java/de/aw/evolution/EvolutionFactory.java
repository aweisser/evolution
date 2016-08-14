package de.aw.evolution;

import de.aw.evolution.domain.Environment;
import de.aw.evolution.domain.Generation;
import de.aw.evolution.domain.Genom;
import de.aw.evolution.domain.Population;
import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.EvolutionaryFactors;
import de.aw.evolution.domain.factors.GeneticDrift;
import de.aw.evolution.domain.factors.Mutation;
import de.aw.evolution.domain.factors.PartnerSelection;
import de.aw.evolution.domain.factors.PhenotypeCreator;
import de.aw.evolution.domain.factors.Recombination;
import de.aw.evolution.domain.factors.Reproduction;
import de.aw.evolution.domain.factors.Selection;

import java.util.function.Supplier;

/**
 * @author armin.weisser
 */
public abstract class EvolutionFactory implements Supplier<Evolution> {


    @Override
    public Evolution get() {
        Population population = new Population(createFirstGeneration());
        Environment environment = createEnvironment();
        Reproduction reproduction = new Reproduction(createSelection(), createPartnerSelection(), createRecombination(), createPhenotypeCreator());
        EvolutionaryFactors evolutionaryFactors = new EvolutionaryFactors(reproduction, createGeneticDrift(), createMutation(), createDeath());
        Evolution evolution = new Evolution(population, environment, evolutionaryFactors);
        return evolution;
    }

    protected abstract Environment createEnvironment();

    protected abstract Generation createFirstGeneration();

    protected abstract Selection createSelection();

    protected abstract PartnerSelection createPartnerSelection();

    protected abstract Recombination createRecombination();

    protected abstract PhenotypeCreator<Genom> createPhenotypeCreator();

    protected abstract GeneticDrift createGeneticDrift();

    protected abstract Mutation createMutation();

    protected abstract Death createDeath();

}
