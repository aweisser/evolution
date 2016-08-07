package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Feature;

import java.util.HashSet;

/**
 * Der Phänotyp (oder das Erscheinungsbild) ist in der Genetik die Menge aller Merkmale (Features) eines Organismus.
 * Der Phänotyp wird durch das Zusammenwirken von Erbanlagen und Umweltfaktoren (Modifikation) bestimmt.
 * Die Modifikation findet auf dem Organismus statt.
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
