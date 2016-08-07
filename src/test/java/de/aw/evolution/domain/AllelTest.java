package de.aw.evolution.domain;

import org.junit.Test;

import java.util.Collections;

import static de.aw.evolution.domain.data.TestDataBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class AllelTest {

    @Test(expected = Exception.class)
    public void creatingAllelWithoutLocusFails() {
        new Allel(null, asSet(aGeneAtLocus(1)));
    }


    @Test(expected = Exception.class)
    public void creatingAllelWithoutGenesFails() {
        new Allel(aGeneLocus(1), Collections.emptySet());
    }


    @Test(expected = Exception.class)
    public void allGenesMustHaveTheCorrectLocus() {
        new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1), aGeneAtLocus(2)));
    }

    @Test
    public void allelHasGeneLocus() {
        Allel allel = new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1), aGeneAtLocus(1)));
        assertThat(allel.getGeneLocus(), is(equalTo(aGeneLocus(1))));
    }

    @Test
    public void allelsWithEqualLocusAreEqual() {
        Allel allel1 = new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1)));
        Allel allel2 = new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1)));
        assertThat(allel1, is(equalTo(allel2)));
    }


    @Test
    public void allelsWithDifferentLocusArenotEqual() {
        Allel allel1 = new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1)));
        Allel allel2 = new Allel(aGeneLocus(2), asSet(aGeneAtLocus(2)));
        assertThat(allel1, is(not(equalTo(allel2))));
    }

    @Test
    public void addingGeneWithDifferentLocusDoesNotAlterTheAllel() {
        Gene gene1 = aGeneAtLocus(1);
        Gene gene2 = aGeneAtLocus(2);
        Allel allel = new Allel(aGeneLocus(1), asSet(gene1));

        assertThat(allel.contains(gene1), is(true));

        assertThat(allel.addVariation(gene2), is(false));
        assertThat(allel.contains(gene2), is(false));
    }

    @Test
    public void theLocusIsTheIdentityOfTheAllel() {
        Allel allel = new Allel(aGeneLocus(1), asSet(aGeneAtLocus(1)));
        assertThat(aGeneLocus(1).hashCode(), is(equalTo(allel.hashCode())));
    }

}
