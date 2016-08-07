package de.aw.evolution.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class FitnessTest {

    @Test
    public void theGreaterTheFitter() {
        assertThat(new Fitness(0.2), is(greaterThan(new Fitness(0.1))));
    }

    @Test
    public void zeroFitnessIsOkAndEqualsAnotherZeroFitness() {
        assertThat(new Fitness(0), is(equalTo(new Fitness(0))));
    }

    @Test
    public void hashCodeIsEqualToValuesHashCode() {
        assertThat(new Fitness(1).hashCode(), is(equalTo(new Double(1).hashCode())));
    }

    @Test
    public void hashValuesDiffer() {
        assertThat(new Fitness(0.1).hashCode(), is(not(equalTo(new Fitness(0.2).hashCode()))));
    }

    @Test(expected = Exception.class)
    public void emptyFitnessCanNotBeCompared() {
        new Fitness.EmptyFitness().compareTo(new Fitness(1));
    }

    @Test(expected = Exception.class)
    public void emptyFitnessCanNotBeComparedTo() {
        new Fitness(1).compareTo(new Fitness.EmptyFitness());
    }

}
