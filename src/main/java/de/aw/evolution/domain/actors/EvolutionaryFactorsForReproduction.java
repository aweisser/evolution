package de.aw.evolution.domain.actors;

/**
 * @author armin.weisser
 */
public class EvolutionaryFactorsForReproduction {

    private final Selection selection;

    private final PartnerSelection partnerSelection;

    private final Recombination recombination;

    public EvolutionaryFactorsForReproduction(Selection selection, PartnerSelection partnerSelection, Recombination recombination) {
        this.selection = selection;
        this.partnerSelection = partnerSelection;
        this.recombination = recombination;
    }

    public Selection getSelection() {
        return selection;
    }

    public PartnerSelection getPartnerSelection() {
        return partnerSelection;
    }

    public Recombination getRecombination() {
        return recombination;
    }

}
