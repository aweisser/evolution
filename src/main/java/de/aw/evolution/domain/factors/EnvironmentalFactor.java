package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Fitness;
import de.aw.evolution.domain.Organism;

import java.util.function.Function;

/**
 * Ein Umweltfaktor beinflusst das Erscheinigungsbild der Organismen und ist Teil der Umwelt.
 *
 * @author armin.weisser
 */
public interface EnvironmentalFactor  extends Function<Organism, Fitness> {

}
