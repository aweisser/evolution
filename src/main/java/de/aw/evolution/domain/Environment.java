package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.EnvironmentalFactor;

import java.util.HashSet;
import java.util.function.Function;

/**
 * Die Umwelt, in der sich die Organismen befinden, wird durch eine Menge von Umweltfaktoren beschrieben.
 * Sie können das Erscheinungsbild (Phänotypen) der Organisamen beeinflussen und bestimmen dessen Fitness.
 *
 * @author armin.weisser
 */
public class Environment extends HashSet<EnvironmentalFactor> implements Function<Organism, Fitness> {

    @Override
    public Fitness apply(Organism organism) {
        // TODO introduce weight on EnvironmentalFactors
        double totalFitness = stream()
                .mapToDouble(ef -> ef.apply(organism).getValue())
                .reduce(0, (a, b) -> (a + b) / 2); // TODO make this a weighted average
        return new Fitness(totalFitness);
    }

}
