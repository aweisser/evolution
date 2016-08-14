package de.aw.evolution.domain;

import de.aw.evolution.domain.factors.Reproduction;

import java.util.Iterator;
import java.util.Optional;

/**
 * Eine Generation ist in der Biologie die Gesamtheit aller Lebewesen, die innerhalb ihrer Abstammungsgruppe
 * denselben Abstand von den gemeinsamen Vorfahren beziehungsweise ihren Nachkommen haben
 *
 * @author armin.weisser
 */
public class Generation extends Group {

    private final Long level;
    private final Generation parentGeneration;
    private Generation childGeneration;

    public static Generation createFirstGeneration() {
        return new Generation();
    }

    private Generation() {
        this.level = 1L;
        this.parentGeneration = null;
    }

    private Generation(Generation parentGeneration) {
        this.level = parentGeneration.level+1;
        this.parentGeneration = parentGeneration;
    }

    public Generation getParentGeneration() {
        return parentGeneration;
    }

    public Generation getChildGeneration() {
        return childGeneration;
    }

    public Generation createNexGeneration(Reproduction reproduction) {
        if(childGeneration == null) {
            childGeneration = new Generation(this);
        }
        createProgeny(reproduction);
        return childGeneration;
    }

    private void createProgeny(Reproduction reproduction) {
        Group selectedGroup = reproduction.getSelection().apply(this);
        Iterator<Organism> partnerPool = selectedGroup.iterator();
        while(partnerPool.hasNext()) {
            Organism mother = partnerPool.next();
            partnerPool.remove();
            Optional<Organism> fatherOptional = reproduction.getPartnerSelection().apply(selectedGroup);
            fatherOptional.ifPresent( father -> {
                Couple couple = new Couple(mother.getGenom(), father.getGenom());
                Genom childGenom = reproduction.getRecombination().apply(couple);
                new Organism(childGeneration, childGenom, reproduction.getPhenotypeCreator()); // TODO Can we derive a initial Phenotype for this child?
            });
        }
    }
}
