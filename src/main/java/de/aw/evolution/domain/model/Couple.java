package de.aw.evolution.domain.model;

/**
 * @author armin.weisser
 */
public class Couple {

    private final Genom father;

    private final Genom mother;

    public Couple(Genom mother, Genom father) {
        this.father = father;
        this.mother = mother;
    }

    public Genom getFather() {
        return father;
    }

    public Genom getMother() {
        return mother;
    }
}
