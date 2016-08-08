package de.aw.evolution.domain;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aFitnessOf;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganismn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class EnvironmentTest {

    @Test
    public void shouldReturnTheAverageFitnessToEachEnvironmentalFactor() {
        Environment environment = new Environment();
        environment.put(organism -> aFitnessOf(0), 1);
        environment.put(organism -> aFitnessOf(1), 1);

        Fitness fitness = environment.apply(anOrganismn());
        assertThat(fitness, is(equalTo(aFitnessOf(0.5))));
    }

    @Test
    public void shouldReturn1IfThereAreNoEnvironmentalFactors() {
        Environment environment = new Environment();
        Fitness fitness = environment.apply(anOrganismn());
        assertThat(fitness, is(equalTo(aFitnessOf(1))));
    }

}