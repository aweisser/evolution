package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Group;
import de.aw.evolution.domain.model.Organism;
import de.aw.evolution.util.FitnessComparator;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author armin.weisser
 */
public interface Death extends EvolutionaryFactor<Group, Group> {

    static Death none() {
        return group -> new Group.EmptyGroup();
    }

    static Death standard() {
        Random random = new Random();
        return group -> {
            Group deadOrganisms = new Group();
            deadOrganisms.addAll(group.stream()
                    .filter( o -> !o.getFitness().isEmpty())
                    .filter(o -> o.getFitness().getValue() < random.nextDouble())
                    .collect(Collectors.toSet()));
            OptionalDouble averageLiving = group.stream().filter( o -> !deadOrganisms.contains(o)).mapToDouble(o -> o.getFitness().getValue()).average();
            OptionalDouble averageDead = deadOrganisms.stream().mapToDouble(o -> o.getFitness().getValue()).average();
            return deadOrganisms;
        };
    }

    static Death forLessThanAverage() {
        return group -> {
            OptionalDouble average = group.stream().mapToDouble(o -> o.getFitness().getValue()).average();
            if(!average.isPresent()) {
                return new Group.EmptyGroup();
            }
            Group deadOrganisms = new Group();
            deadOrganisms.addAll(group.stream()
                    .filter( o -> !o.getFitness().isEmpty())
                    .filter(o -> o.getFitness().getValue() <= average.getAsDouble())
                    .collect(Collectors.toSet()));
            OptionalDouble averageLiving = group.stream().filter( o -> !deadOrganisms.contains(o)).mapToDouble(o -> o.getFitness().getValue()).average();
            OptionalDouble averageDead = deadOrganisms.stream().mapToDouble(o -> o.getFitness().getValue()).average();
            return deadOrganisms;
        };
    }

    static Death forWeakest(float threshold) {
        Random random = new Random();
        return group -> {
            Group deadOrganisms = new Group();
            List<Organism> organismList = group.stream()
                    .filter(o -> !o.getFitness().isEmpty())
                    .collect(Collectors.toList());
            organismList.sort(new FitnessComparator().reversed());
            Integer offset = Math.round(organismList.size() * threshold);
            deadOrganisms.addAll(organismList.subList(0, offset));
            OptionalDouble averageLiving = group.stream().filter( o -> !deadOrganisms.contains(o)).mapToDouble(o -> o.getFitness().getValue()).average();
            OptionalDouble averageDead = deadOrganisms.stream().mapToDouble(o -> o.getFitness().getValue()).average();
            return deadOrganisms;
        };
    }


}
