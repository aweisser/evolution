package de.aw.evolution.domain;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGenom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

/**
 * @author armin.weisser
 */
public class GenomTest {

    @Test
    public void aCopyOfAGenomIsACopyOfAllGenes() {
        Genom genom = aGenom();
        Genom copy = genom.clone();
        assertThat(copy, is(not(sameInstance(genom))));
        assertThat(copy.size(), is(equalTo(genom.size())));



    }

}