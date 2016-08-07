package de.aw.evolution.domain;

import de.aw.evolution.util.FitnessComparator;
import org.junit.Test;

import java.util.List;

import static de.aw.evolution.domain.data.TestDataBuilder.anOrganismn;
import static de.aw.evolution.domain.data.TestDataBuilder.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author armin.weisser
 */
public class FitnessComparatorTest {

    @Test
    public void organismsShouldBeSortableByFitness() {
        Organism organism1 = anOrganismn();
        organism1.setFitness(new Fitness(1));

        Organism organism2 = anOrganismn();
        organism2.setFitness(new Fitness(2));

        List<Organism> organisms = asList(organism1, organism2);
        organisms.sort(new FitnessComparator());

        assertThat(organisms.get(0), is(equalTo(organism2)));
        assertThat(organisms.get(1), is(equalTo(organism1)));
    }
}
