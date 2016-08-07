package de.aw.evolution.domain;

import org.junit.Test;

import java.util.Set;

import static de.aw.evolution.domain.data.TestDataBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class GenePoolTest {

    @Test
    public <T> void aGenePoolContainsAllGeneVariationsOfTheGivenOrganisms() {

        Phenotype phenotype = null;

        Gene brownHairGene = aGeneAtLocus(1);
        Gene blackHairGene = aGeneAtLocus(1);
        Gene blueEyesGene = aGeneAtLocus(2);

        Genom genomTim = new Genom(asSet(brownHairGene, blueEyesGene));
        Genom genomPaul = new Genom(asSet(blackHairGene, blueEyesGene));

        Generation generation1 = Generation.createFirstGeneration();

        Set<Organism> organisms = asSet(
                anOrganismn(generation1, genomTim, phenotype),
                anOrganismn(generation1, genomPaul, phenotype)
        );

        GenePool genePool = GenePool.create(organisms);

        assertThat(genePool.contains(brownHairGene), is(true));
        assertThat(genePool.contains(blueEyesGene), is(true));
        assertThat(genePool.contains(blackHairGene), is(true));
    }


}
