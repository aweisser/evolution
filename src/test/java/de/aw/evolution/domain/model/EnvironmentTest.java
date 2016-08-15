package de.aw.evolution.domain.model;

import de.aw.evolution.domain.data.TestDataBuilder;
import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aFitnessOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author armin.weisser
 */
public class EnvironmentTest {

    @Test
    public void shouldReturnTheAverageFitnessToEachEnvironmentalFactor() {
        Environment environment = new Environment();
        environment.put(organism -> aFitnessOf(0), 1);
        environment.put(organism -> aFitnessOf(1), 1);

        Fitness fitness = environment.apply(TestDataBuilder.anOrganism());
        assertThat(fitness, is(equalTo(aFitnessOf(0.5))));
    }

    @Test
    public void shouldReturnTheWeightedAverageFitnessToEachEnvironmentalFactor() {
        Environment environment = new Environment();
        environment.put(organism1 -> aFitnessOf(0.25), 1);   // nice haircut (ok. we don't have a nice hair cut. But it's very unimportant).
        environment.put(organism1 -> aFitnessOf(0.75), 10);  // money (lot's of money there. 75%. It's more or less important)
        environment.put(organism -> aFitnessOf(0.05), 100); // food (only 5% on food. But this is VERY importand -> weight 100)

        Fitness fitness = environment.apply(TestDataBuilder.anOrganism());
        assertThat(fitness, is(greaterThan(aFitnessOf(0.114))));
        assertThat(fitness, is(lessThan(aFitnessOf(0.115))));
    }

    @Test
    public void shouldReturn1IfThereAreNoEnvironmentalFactors() {
        Environment environment = new Environment();
        Fitness fitness = environment.apply(TestDataBuilder.anOrganism());
        assertThat(fitness, is(equalTo(aFitnessOf(1))));
    }

}