package de.aw.evolution.util;

import de.aw.evolution.domain.model.Organism;

import java.util.Comparator;

/**
 * @author armin.weisser
 */
public class FitnessComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        // The fittest Organism is on top of a sorted list
        return -o1.getFitness().compareTo(o2.getFitness());
    }
}
