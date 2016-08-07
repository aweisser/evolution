package de.aw.evolution.domain;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class GenomTest {

    @Test
    public void aCopyOfAGenomIsACopyOfAlGenes() {
        Genom genom = aGenom();
        Genom copy = genom.clone();
        assertThat(copy.getGenes(), is(not(equalTo(genom.getGenes()))));
        assertThat(copy.getGenes(), is(not(sameInstance(genom.getGenes()))));
        assertThat(copy.getGenes(), hasSize(genom.getGenes().size()));
    }

}