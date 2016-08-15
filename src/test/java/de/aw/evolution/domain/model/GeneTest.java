package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGeneticInformation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author armin.weisser
 */
public class GeneTest {

    @Test(expected = Exception.class)
    public void aGeneMustHaveGenlocus() {
        new Gene(null);
    }

    @Test
    public void genesWithSameIdAndLocusAreEqual() {
        GeneLocus locus = new GeneLocus(1);
        assertThat(new Gene(locus), is(equalTo(new Gene(locus))));
    }

    @Test
    public void genesWithDifferentGeneticInformationOnSameLocusAreNotEqual() {
        GeneticInformation<String> data = aGeneticInformation("xxx");
        GeneticInformation<String> otherData = aGeneticInformation("yyy");
        assertThat(aGeneAtLocus(1, data), is(not(equalTo(aGeneAtLocus(1, otherData)))));
    }

    @Test
    public void genesWithSameGeneticInformationAndDifferntLocusAreNotEqual() {
        GeneticInformation<String> data = aGeneticInformation("xxx");
        assertThat(aGeneAtLocus(1, data), is(not(equalTo(aGeneAtLocus(2, data)))));
    }

    @Test
    public void geneShouldHaveStringRepresantationContainingTheData() {
        GeneticInformation<String> data = aGeneticInformation("xxx");
        Gene gene = aGeneAtLocus(1, data);
        assertThat(gene.toString(), containsString(data.toString()));
    }

    @Test
    public void aGeneWithoutGeneticInformationIsInitializedWith_NoGeneticInformation() {
        Gene gene = new Gene(new GeneLocus(1));
        assertThat(gene.getGeneticInformation(), is(equalTo(new GeneticInformation.NoGeneticInformation())));
    }



}
