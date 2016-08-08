package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.EnvironmentalFactor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Die Umwelt, in der sich die Organismen befinden, wird durch eine Menge von Umweltfaktoren beschrieben.
 * Sie können das Erscheinungsbild (Phänotypen) der Organisamen beeinflussen und bestimmen dessen Fitness.
 *
 * @author armin.weisser
 */
public class Environment extends HashMap<EnvironmentalFactor, Integer> implements Function<Organism, Fitness> {

    @Override
    public Fitness apply(Organism organism) {
        if(isEmpty()) {
            return new Fitness(1);
        }
        return weightedFitness(organism);
    }

    public Fitness weightedFitness(Organism organism) {
        double weightedFitnessValue = entrySet().stream()
                .mapToDouble(entry -> weightedFitnessValue(organism, entry.getKey(), entry.getValue()))
                .sum();
        return new Fitness(weightedFitnessValue);
    }

    public double weightedFitnessValue(Organism organism, EnvironmentalFactor environmentalFactor, Integer weight) {
        Double fitnessValue = environmentalFactor.apply(organism).getValue();
        Double factor = factor(weight);
        return fitnessValue * factor;
    }

    public double factor(Integer weight) {
        return new BigDecimal(weight).divide(new BigDecimal(size())).doubleValue();
    }

}
