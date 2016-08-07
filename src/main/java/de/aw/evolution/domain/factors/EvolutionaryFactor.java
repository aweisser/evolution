package de.aw.evolution.domain.factors;

import java.util.function.Function;

/**
 * Als Evolutionsfaktor bezeichnet man in der Biologie Prozesse,
 * durch die der Genpool – das ist die Gesamtheit aller Genvariationen in einer Population – verändert wird.
 *
 * @author armin.weisser
 */
public interface EvolutionaryFactor<IN, OUT> extends Function<IN, OUT> {

}
