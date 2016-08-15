package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Genom;

/**
 * @author armin.weisser
 */
public class Reproduction {

    private final Selection selection;

    private final PartnerSelection partnerSelection;

    private final Recombination recombination;

    private final PhenotypeCreator<Genom> phenotypeCreator;

    public Reproduction(Selection selection, PartnerSelection partnerSelection, Recombination recombination, PhenotypeCreator<Genom> phenotypeCreator) {
        this.selection = selection;
        this.partnerSelection = partnerSelection;
        this.recombination = recombination;
        this.phenotypeCreator = phenotypeCreator;
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

    public PhenotypeCreator<Genom> getPhenotypeCreator() {
        return phenotypeCreator;
    }
}
