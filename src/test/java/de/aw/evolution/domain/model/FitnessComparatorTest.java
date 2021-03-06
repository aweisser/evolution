package de.aw.evolution.domain.model;

import de.aw.evolution.domain.data.TestDataBuilder;
import de.aw.evolution.util.FitnessComparator;
import org.junit.Test;

import java.util.List;

import static de.aw.evolution.domain.data.TestDataBuilder.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author armin.weisser
 */
public class FitnessComparatorTest {

    @Test
    public void organismsShouldBeSortableByFitness() {
        Organism organism1 = TestDataBuilder.anOrganism();
        organism1.setFitness(new Fitness(1));

        Organism organism2 = TestDataBuilder.anOrganism();
        organism2.setFitness(new Fitness(2));

        List<Organism> organisms = asList(organism1, organism2);
        organisms.sort(new FitnessComparator());

        assertThat(organisms.get(0), is(equalTo(organism2)));
        assertThat(organisms.get(1), is(equalTo(organism1)));
    }
}
