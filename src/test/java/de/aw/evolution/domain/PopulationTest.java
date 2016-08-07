package de.aw.evolution.domain;

import de.aw.evolution.domain.actors.EvolutionaryFactorsForReproduction;
import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganismn;
import static de.aw.evolution.domain.data.TestDataBuilder.asSet;
import static de.aw.evolution.domain.data.TestDataBuilder.defaultEvolutionaryFactorsForReproduction;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class PopulationTest {

    @Test
    public void aPopulationsGenePoolContainsAllGenesOfAllGenerations() {

        Phenotype phenotype = null;

        Generation generation1 = Generation.createFirstGeneration();
        Population population = new Population(generation1);
        Generation generation2 = population.reproduce(defaultEvolutionaryFactorsForReproduction());

        Gene brownHairGene = aGeneAtLocus(1);
        Gene blackHairGene = aGeneAtLocus(1);
        Gene blueEyesGene = aGeneAtLocus(2);
        Gene greenEyesGene = aGeneAtLocus(2);

        Genom genomTim = new Genom(asSet(brownHairGene, blueEyesGene));
        Genom genomPaul = new Genom(asSet(blackHairGene, blueEyesGene));
        Genom genomLisa = new Genom(asSet(blackHairGene, greenEyesGene));

        generation1.add(anOrganismn(generation1, genomTim, phenotype));
        generation1.add(anOrganismn(generation1, genomPaul, phenotype));
        generation2.add(anOrganismn(generation2, genomLisa, phenotype));

        assertThat(population.getGenePool().
                containsAll(asSet(
                    brownHairGene,
                    blackHairGene,
                    blueEyesGene,
                    greenEyesGene)), is(true));

    }

    @Test(expected = Exception.class)
    public void aPopulationMustHaveAFirstGeneration() {
        new Population(null);
    }

}
