package de.aw.evolution.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(new GeneLocus(1), is(not(equalTo(new GeneLocus(2)))));
    }


    @Test
    public void locusShouldHaveStringRepresantationContainingThePosition() {
        GeneLocus geneLocus = new GeneLocus(1);
        assertThat(geneLocus.toString(), containsString("position=1"));
    }
}
