package de.aw.evolution.domain;

import de.aw.evolution.domain.actors.EnvironmentalFactor;

import java.util.HashSet;

/**
 * Die Umwelt, in der sich die Organismen befinden, wird durch eine Menge von Umweltfaktoren beschrieben.
 * Sie können das Erscheinungsbild (Phänotypen) der Organisamen beeinflussen.
 *
 * @author armin.weisser
 */
public class Environment extends HashSet<EnvironmentalFactor> {

}
