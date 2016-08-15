package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aFeature;
import static de.aw.evolution.domain.data.TestDataBuilder.aGenom;
import static de.aw.evolution.domain.data.TestDataBuilder.anEmptyGeneration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class OrganismTest {
    
    @Test
    public void organismShouldUseInternalPhenotypeInstance() {
        Phenotype phenotype = new Phenotype();
        phenotype.add(aFeature());
        phenotype.add(aFeature());
        Organism organism = new Organism(anEmptyGeneration(), aGenom(), phenotype);

        assertThat(phenotype.size(), is(equalTo(organism.getPhenotype().size())));

        // Changes to the original Phenotype instance don't affect the internal one
        phenotype.add(aFeature());

        assertThat(phenotype.size(), is(equalTo(3)));
        assertThat(organism.getPhenotype().size(), is(equalTo(2)));
    }


}
