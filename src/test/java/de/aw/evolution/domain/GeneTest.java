package de.aw.evolution.domain;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class GeneTest {

    @Test(expected = Exception.class)
    public void aGeneMustHaveGenlocus() {
        new Gene(null, UUID.randomUUID());
    }

    @Test(expected = Exception.class)
    public void aGeneMustHaveAnId() {
        new Gene(new GeneLocus(1), null);
    }

    @Test
    public void genesWithSameIdAndLocusAreEqual() {
        UUID id = UUID.randomUUID();
        GeneLocus locus = new GeneLocus(1);
        assertThat(new Gene(locus, id), is(equalTo(new Gene(locus, id))));
    }

    @Test
    public void genesWithDifferentIdAndLocusAreNotEqual() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        GeneLocus locus = new GeneLocus(1);
        assertThat(new Gene(locus, id1), is(not(equalTo(new Gene(locus, id2)))));
    }

    @Test
    public void genesWithSameIdAndDifferntLocusAreNotEqual() {
        UUID id = UUID.randomUUID();
        GeneLocus locus1 = new GeneLocus(1);
        GeneLocus locus2 = new GeneLocus(2);
        assertThat(new Gene(locus1, id), is(not(equalTo(new Gene(locus2, id)))));
    }

    @Test
    public void geneShouldHaveStringRepresantationContainingTheId() {
        UUID id = UUID.randomUUID();
        Gene gene = new Gene(new GeneLocus(1), id);
        assertThat(gene.toString(), containsString(id.toString()));
    }



}
