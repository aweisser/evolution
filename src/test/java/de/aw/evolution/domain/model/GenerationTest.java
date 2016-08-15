package de.aw.evolution.domain.model;

import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGenerationOfSize;
import static de.aw.evolution.domain.data.TestDataBuilder.defaultReproduction;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class GenerationTest {

    @Test
    public void reproductionDoesNotReduceTheCurrentGeneration() {

        Generation generation = aGenerationOfSize(10);
        generation.createNexGeneration(defaultReproduction());

        assertThat(generation.size(), is(equalTo(10)));

    }

}