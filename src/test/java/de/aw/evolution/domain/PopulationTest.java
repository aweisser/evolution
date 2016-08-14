package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Mutation;
import org.junit.Test;

import java.util.UUID;

import static de.aw.evolution.domain.data.TestDataBuilder.aFitnessOf;
import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGenerationOfSize;
import static de.aw.evolution.domain.data.TestDataBuilder.aGenocide;
import static de.aw.evolution.domain.data.TestDataBuilder.anEmptyGeneration;
import static de.aw.evolution.domain.data.TestDataBuilder.anEnvironment;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganism;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganismWith;
import static de.aw.evolution.domain.data.TestDataBuilder.asSet;
import static de.aw.evolution.domain.data.TestDataBuilder.defaultReproduction;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author armin.weisser
 */
public class PopulationTest {

    @Test
    public void aPopulationsGenePoolContainsAllGenesOfAllGenerations() {

        Generation generation1 = Generation.createFirstGeneration();
        Population population = new Population(generation1);
        Generation generation2 = population.apply(defaultReproduction());

        Gene brownHairGene = aGeneAtLocus(1);
        Gene blackHairGene = aGeneAtLocus(1);
        Gene blueEyesGene = aGeneAtLocus(2);
        Gene greenEyesGene = aGeneAtLocus(2);

        Genom genomTim = new Genom(asSet(brownHairGene, blueEyesGene));
        Genom genomPaul = new Genom(asSet(blackHairGene, blueEyesGene));
        Genom genomLisa = new Genom(asSet(blackHairGene, greenEyesGene));

        generation1.add(anOrganism(generation1, genomTim));
        generation1.add(anOrganism(generation1, genomPaul));
        generation2.add(anOrganism(generation2, genomLisa));

        assertThat(population.getGenePool().
                containsAll(asSet(
                    brownHairGene,
                    blackHairGene,
                    blueEyesGene,
                    greenEyesGene)), is(true));
    }



    @Test
    public void aPopulationHasAGeneration() {
        assertThat(new Population().getCurrentGeneration(), is(notNullValue()));
    }

    @Test
    public void applyingEnvironmentShouldAddFitnessValueToIndividuals() {
        Organism organism = anOrganism();
        assertThat(organism.getFitness(), is(instanceOf(Fitness.EmptyFitness.class)));
        new Population(organism.getGeneration()).apply(anEnvironment());
        assertThat(organism.getFitness(), is(not(instanceOf(Fitness.EmptyFitness.class))));
        assertThat(organism.getFitness().getValue(), is(notNullValue()));
    }

    @Test
    public void aGenocideShouldKillAllIndividuals() {
        Population population = new Population(aGenerationOfSize(10));
        assertThat(population.size(), is(10));
        population.apply(aGenocide());
        assertThat(population.size(), is(0));
    }

    @Test
    public void aMutationShouldChangeTheGenPool() {
        Population population = new Population(aGenerationOfSize(2));
        GenePool initialGenePool = population.getGenePool();
        population.apply((Mutation) genom -> {
            genom.forEach( gene -> gene.getGeneticInformation().setData(UUID.randomUUID()));
            return genom;
        });
        GenePool modifiedGenePool = population.getGenePool();
        assertThat(initialGenePool, is(not(equalTo(modifiedGenePool))));
    }

    @Test
    public void anIdentiyMutationShouldNotChangeTheGenPool() {
        Population population = new Population(aGenerationOfSize(2));
        GenePool initialGenePool = population.getGenePool();
        population.apply((Mutation) genom -> genom.clone());
        GenePool modifiedGenePool = population.getGenePool();
        assertThat(initialGenePool, is(equalTo(modifiedGenePool)));
    }

    @Test
    public void avgFitnessOfEmptyPopulationShouldBeAnEmptyFitness() {
        Population population = new Population(anEmptyGeneration());
        assertThat(population.getAverageFitness(), instanceOf(Fitness.EmptyFitness.class));
    }

    @Test
    public void avgFitnessOfNonEmptyPopulationWhereAllOrganismsHaveEmptyFitnessShouldBeAnEmptyFitness() {
        Population population = new Population(aGenerationOfSize(2));
        population.getIndividuals().forEach( o -> assertThat(o.getFitness(), instanceOf(Fitness.EmptyFitness.class)));
        assertThat(population.getAverageFitness(), instanceOf(Fitness.EmptyFitness.class));
    }


    @Test
    public void avgFitnessOfNonEmptyPopulationShouldIgnoreOrganismsWithEmptyFitness() {
        Population population = new Population(anEmptyGeneration());
        anOrganism(population.getCurrentGeneration());
        anOrganismWith(aFitnessOf(0), population.getCurrentGeneration());
        anOrganismWith(aFitnessOf(1), population.getCurrentGeneration());

        assertThat(population.getAverageFitness(), is(equalTo(aFitnessOf(0.5))));
    }


    @Test
    public void populationStringRepresentationShouldContainAverageFitness() {
        Population population = new Population(anEmptyGeneration());
        assertThat(population.toString(), containsString("averageFitness"));
    }

    @Test
    public void populationStringRepresentationShouldContainAverageSize() {
        Population population = new Population(anEmptyGeneration());
        assertThat(population.toString(), containsString("size"));
    }

    @Test
    public void populationStringRepresentationShouldContainCurrentGeneration() {
        Population population = new Population(anEmptyGeneration());
        assertThat(population.toString(), containsString("currentGeneration"));
    }
}
