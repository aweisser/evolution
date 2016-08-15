package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aFitnessOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author armin.weisser
 */
public class FitnessTest {

    @Test
    public void theGreaterTheFitter() {
        assertThat(aFitnessOf(0.2), is(greaterThan(aFitnessOf(0.1))));
    }

    @Test
    public void zeroFitnessIsOkAndEqualsAnotherZeroFitness() {
        assertThat(aFitnessOf(0), is(equalTo(aFitnessOf(0))));
    }

    @Test
    public void hashCodeIsEqualToValuesHashCode() {
        assertThat(aFitnessOf(1).hashCode(), is(equalTo(new Double(1).hashCode())));
    }

    @Test
    public void hashValuesDiffer() {
        assertThat(aFitnessOf(0.1).hashCode(), is(not(equalTo(aFitnessOf(0.2).hashCode()))));
    }

    @Test(expected = Exception.class)
    public void emptyFitnessCanNotBeCompared() {
        new Fitness.EmptyFitness().compareTo(aFitnessOf(1));
    }

    @Test(expected = Exception.class)
    public void emptyFitnessDoesNotHaveValue() {
        new Fitness.EmptyFitness().getValue();
    }


    @Test(expected = Exception.class)
    public void emptyFitnessCanNotBeComparedTo() {
        aFitnessOf(1).compareTo(new Fitness.EmptyFitness());
    }

    @Test
    public void shouldHaveAStringRepresentationHoldingTheFitnessValue() {
        assertThat(aFitnessOf(0.12345).toString(), containsString("0.12345"));
    }
}
