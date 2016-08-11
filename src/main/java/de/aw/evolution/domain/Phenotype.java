package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Feature;

import java.util.HashSet;

/**
 * Der Phänotyp (oder das Erscheinungsbild) ist in der Genetik die Menge aller Merkmale (Features) eines Organismus.
 * Der Phänotyp wird durch das Zusammenwirken von Erbanlagen (Genotyp) und Umweltfaktoren (Modifikation) bestimmt.
 *
 * Ein Großteil des Phänotyps ist durch den Genotyp vorbestimmt.
 * Die phänotypische Plastizität gibt an, inwieweit der Phänotyp zusätzlich von der Umwelt beeinflusst werden kann.
 * Beispiel: Bestimmte Gräser wachsen in 1500 m Höhe weniger hoch wie im Tal, obwohl sie den gleichen Genotyp haben.
 *
 * @author armin.weisser
 */
public class Phenotype extends HashSet<Feature> {

    public Phenotype() {
        super();
    }

    public Phenotype(Phenotype phenotype) {
        this();
        this.addAll(phenotype);
    }
}
