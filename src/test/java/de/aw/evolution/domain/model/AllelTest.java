package de.aw.evolution.domain.model;

import org.junit.Test;

import java.util.Collections;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGeneLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.asSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
    public void shouldContainPositionAsPartOfTheStringRepresentation() {
        Allel allel = new Allel(aGeneLocus(4711), asSet(aGeneAtLocus(4711)));
        assertThat(allel.toString(), containsString("4711"));
    }

    @Test
    public void allelsWithSamePositionAndGenesHaveEqualHashValues() {
        Allel allel1 = new Allel(aGeneLocus(4711), asSet(aGeneAtLocus(4711)));
        Allel allel2 = new Allel(aGeneLocus(4711), asSet(aGeneAtLocus(4711)));
        assertThat(allel1.hashCode(), is(equalTo(allel2.hashCode())));
    }


}
