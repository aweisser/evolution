package de.aw.evolution.domain;

import de.aw.evolution.domain.actors.Modification;

/**
 * @author armin.weisser
 */
public class Organism {

    protected final Genom genom;

    /*
        A Phenotype is unique per Organism.
        It's features may change due to Modification ...
    */
    protected final Phenotype phenotype;

    protected final Generation generation;

    private Fitness fitness = new Fitness.EmptyFitness();

    public Organism(Generation generation, Genom genom) {
        this(generation, genom, null);
    }

    public Organism(Generation generation, Genom genom, Phenotype phenotype) {
        this.generation = generation;
        this.generation.add(this);

        this.genom = genom;

        // A Phenotype is unique per Organism. So we create a new, internal instance a asign it to a final variable.
        if(phenotype == null) {
            this.phenotype = phenotype;
        } else {
            this.phenotype = new Phenotype(phenotype);
        }
    }

    public Genom getGenom() {
        return genom;
    }

    /**
     * Der Phänotyp wird durch das Zusammenwirken von Erbanlagen und Umweltfaktoren bestimmt.
     * Die Veränderung des Phänotyps durch Umweltfaktoren wird Modifikation genannt.
     * Durch die Modifikation verändert sich nur der phenotyp nicht aber das Genom.
     *
     * @param modification
     * @param environment
     */
    public void modifyPhenotype(Environment environment, Modification modification) {
        Genom readOnlyGenom = Genom.copyOf(this.genom); // this.genom should not be modified
        modification.apply(environment, phenotype, readOnlyGenom);
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
}
