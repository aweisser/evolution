package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Modification;
import de.aw.evolution.domain.factors.PhenotypeCreator;

/**
 * Organismen stellen die einzelnen Individuen einer Population dar.
 * Ein Organismus kann immer eindeutig einer Generation von Organismen zugeordnet werden.
 *
 * Ein Organismus wird beschrieben durch den Genotyp (Erbanlagen) und den Phänotype (Eigenschaften und Erscheinungsbild).
 * 
 * @author armin.weisser
 */
public class Organism {

    private Genom genom;

    /**
     * A Phenotype is unique per Organism. The "features" may change due to Modification ...
     */
    private final Phenotype phenotype;

    private final Generation generation;

    private Fitness fitness = new Fitness.EmptyFitness();

    public Organism(Generation generation, Genom genom) {
        this(generation, genom, (Phenotype)null);
    }

    public Organism(Generation generation, Genom genom, PhenotypeCreator<Genom> phenotypeCreator) {
        this(generation, genom, phenotypeCreator.createPhenotypeFrom(genom));
    }

    public Organism(Generation generation, Genom genom, Phenotype phenotype) {
        this.generation = generation;
        this.generation.add(this);

        this.genom = genom;

        // A Phenotype is unique per Organism. So we create a new, internal instance a asign it to a final variable.
        if(phenotype == null) {
            this.phenotype = null;
        } else {
            this.phenotype = new Phenotype(phenotype);
        }
    }

    public Genom getGenom() {
        return genom;
    }

    public void setGenom(Genom genom) {
        this.genom = genom;
    }

    /**
     * Der Phänotyp wird durch das Zusammenwirken von Erbanlagen und Umweltfaktoren bestimmt.
     * Die Veränderung des Phänotyps durch Umweltfaktoren wird Modifikation genannt.
     * Durch die Modifikation verändert sich nur der Phenotyp nicht aber das Genom.
     *
     * @param modification
     * @param environment
     */
    public void modifyPhenotype(Environment environment, Modification modification) {
        modification.apply(environment, this);
    }

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public Generation getGeneration() {
        return generation;
    }
}
