package de.aw.evolution.domain;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class OrganismTest {
    
    @Test
    public void organismShouldUseInternalPhenotypeInstance() {
        Phenotype phenotype = new Phenotype();
        phenotype.add(aFeature());
        phenotype.add(aFeature());
        Organism organism = new Organism(aGeneration(), aGenom(), phenotype);

        assertThat(phenotype.size(), is(equalTo(organism.getPhenotype().size())));

        // Changes to the original Phenotype instance don't affect the internal one
        phenotype.add(aFeature());

        assertThat(phenotype.size(), is(equalTo(3)));
        assertThat(organism.phenotype.size(), is(equalTo(2)));
    }


}
