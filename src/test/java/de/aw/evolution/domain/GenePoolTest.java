package de.aw.evolution.domain;

import org.junit.Test;

import java.util.Set;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGenom;
import static de.aw.evolution.domain.data.TestDataBuilder.anEmptyGeneration;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganism;
import static de.aw.evolution.domain.data.TestDataBuilder.asSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
                anOrganism(generation1, genomTim),
                anOrganism(generation1, genomPaul)
        );

        GenePool genePool = GenePool.create(organisms);

        assertThat(genePool.contains(brownHairGene), is(true));
        assertThat(genePool.contains(blueEyesGene), is(true));
        assertThat(genePool.contains(blackHairGene), is(true));
    }

    @Test
    public void shouldEqualOtherGenPoolWithSameGenes() {
        Generation generation = anEmptyGeneration();

        Genom genom1ab = aGenom(aGeneAtLocus(1, "a"), aGeneAtLocus(2, "b"));
        Genom genom1xy = aGenom(aGeneAtLocus(1, "x"), aGeneAtLocus(2, "y"));

        Organism organism1 = anOrganism(generation, genom1ab);
        Organism organism2 = anOrganism(generation, genom1xy);

        GenePool genePool1 = GenePool.create(asSet(organism1, organism2));
        GenePool genePool2 = GenePool.create(asSet(organism1, organism2));

        assertThat(genePool1, is(equalTo(genePool2)));
        assertThat(genePool1.hashCode(), is(equalTo(genePool2.hashCode())));
    }

    @Test
    public void shouldNotEqualOtherGenPoolWithSameGenesAtDifferentLocus() {
        Generation generation = anEmptyGeneration();

        Genom genom1a2b = aGenom(aGeneAtLocus(1, "a"), aGeneAtLocus(2, "b"));
        Genom genom1x2y = aGenom(aGeneAtLocus(1, "x"), aGeneAtLocus(2, "y"));

        Genom genom3a4b = aGenom(aGeneAtLocus(3, "a"), aGeneAtLocus(4, "b"));
        Genom genom3x4y = aGenom(aGeneAtLocus(3, "x"), aGeneAtLocus(4, "y"));

        Organism organism1a2b = anOrganism(generation, genom1a2b);
        Organism organism1x2y = anOrganism(generation, genom1x2y);

        Organism organism3a4b = anOrganism(generation, genom3a4b);
        Organism organism3x4y = anOrganism(generation, genom3x4y);

        GenePool genePool1 = GenePool.create(asSet(organism1a2b, organism1x2y));
        GenePool genePool2 = GenePool.create(asSet(organism3a4b, organism3x4y));

        assertThat(genePool1, is(not(equalTo(genePool2))));
        assertThat(genePool1.hashCode(), is(not(equalTo(genePool2.hashCode()))));

    }

    @Test
    public void shouldNotEqualOtherGenPoolWithDifferentGenesAtSameLocus() {
        Generation generation = anEmptyGeneration();

        Genom genom1a2b = aGenom(aGeneAtLocus(1, "a"), aGeneAtLocus(2, "b"));
        Genom genom1x2y = aGenom(aGeneAtLocus(1, "x"), aGeneAtLocus(2, "y"));

        Genom genom1b2a = aGenom(aGeneAtLocus(1, "b"), aGeneAtLocus(2, "a"));
        Genom genom1y2x = aGenom(aGeneAtLocus(1, "y"), aGeneAtLocus(2, "x"));

        Organism organism1a2b = anOrganism(generation, genom1a2b);
        Organism organism1x2y = anOrganism(generation, genom1x2y);

        Organism organism1b2a = anOrganism(generation, genom1b2a);
        Organism organism1y2x = anOrganism(generation, genom1y2x);

        GenePool genePool1 = GenePool.create(asSet(organism1a2b, organism1x2y));
        GenePool genePool2 = GenePool.create(asSet(organism1b2a, organism1y2x));

        assertThat(genePool1, is(not(equalTo(genePool2))));
        assertThat(genePool1.hashCode(), is(not(equalTo(genePool2.hashCode()))));

    }

}
