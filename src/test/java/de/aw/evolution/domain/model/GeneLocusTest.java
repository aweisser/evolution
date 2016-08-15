package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGeneLocus;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author armin.weisser
 */
public class GeneLocusTest {

    @Test
    public void geneLocusWithSamePositionsAreEqual() {
        assertThat(new GeneLocus(1), is(equalTo(new GeneLocus(1))));
    }

    @Test
    public void geneLocusWithDifferentPositionsAreNotEqual() {
        assertThat(aGeneAtLocus(1), is(not(equalTo(aGeneLocus(2)))));
    }


    @Test
    public void locusShouldHaveStringRepresantationContainingThePosition() {
        assertThat(aGeneLocus(1).toString(), containsString("position=1"));
    }

    @Test
    public void locusWithGreaterPositionAreGreater() {
        assertThat(aGeneLocus(2), is(greaterThan(aGeneLocus(1))));
    }
}
