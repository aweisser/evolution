package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Fitness;
import de.aw.evolution.domain.Organism;

import java.util.function.Function;

/**
 * Ein Umweltfaktor beinflusst das Erscheinigungsbild (Phänotyp) eines Organimus.
 * Darüber hinaus bestimmt er auch die Fitness. // TODO Seperation of concerns?
 *
 * @author armin.weisser
 */
public interface EnvironmentalFactor extends Function<Organism, Fitness> {

}
