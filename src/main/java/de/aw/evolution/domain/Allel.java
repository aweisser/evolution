package de.aw.evolution.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Als Allel bezeichnet man die alternativen Formen eines Gens mit jeweils gleichem Genlocus .
 *
 * Als Polymorphismus bezeichnet man im Bereich Genetik das Auftreten mehrerer Genvarianten innerhalb einer Population.
 * Die verschiedenen Varianten eines bestimmten Gens am gleichen Genort (Locus) werden auch Allele genannt.
 *
 * @author armin.weisser
 */
public class Allel {

    private final GeneLocus geneLocus;
    private final Set<Gene> varitions = new HashSet<>();

    public Allel(GeneLocus geneLocus, Set<Gene> varitions) {
        // TODO who should be able to create allels?
        // Who makes sure, that there is only one allel at each locus? The GenePool could be a Allel-Factory ...
        // The Allel class could be an inner class of GenPool ...
        assertGenelocus(geneLocus);
        this.geneLocus = geneLocus;

        assertVariations(geneLocus, varitions);
        this.varitions.addAll(varitions);
    }

    private void assertGenelocus(GeneLocus geneLocus) {
        if(geneLocus == null) {
            throw new IllegalArgumentException("Genlocus may not be null. The Genlocus is the identifier for an allel.");
        }
    }

    private void assertVariations(final GeneLocus geneLocus, Set<Gene> varitions) {
        if(varitions == null || varitions.isEmpty()) {
            throw new IllegalArgumentException("Provide at least one Gene");
        }
        Set<Gene> invalidVaritions = varitions.stream()
                .filter(gen -> !gen.getLocus().equals(geneLocus))
                .collect(Collectors.<Gene>toSet());

        if(invalidVaritions.size() > 0) {
            throw new IllegalArgumentException(
                            "All variations must have the specified locus " + geneLocus +
                            ".\n Invalid varitions are:\n" +
                            invalidVaritions);
        }
    }

    /**
     *
     * @return the identifier of this allel.
     */
    public GeneLocus getGeneLocus() {
        return geneLocus;
    }

    /**
     *
     * @param gene
     * @return true, if the gene is part of this allel
     */
    public boolean contains(Gene gene) {
        return geneLocus.equals(gene.getLocus()) && varitions.contains(gene);
    }

    /**
     * Only a gene with the same locus as this allel will be added ...
     *
     * @param gene a gene with the same locus as this allel
     * @return true, if the gene was added to the allel
     */
    public boolean addVariation(Gene gene) {
        if(gene.getLocus().equals(this.geneLocus)) {
            return this.varitions.add(gene);
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Allel)) return false;

        Allel allel = (Allel) o;

        if (!getGeneLocus().equals(allel.getGeneLocus())) return false;
        return varitions.equals(allel.varitions);

    }

    @Override
    public int hashCode() {
        int result = getGeneLocus().hashCode();
        result = 31 * result + varitions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Allel{" +
                "geneLocus=" + geneLocus +
                ", varitions=" + varitions.size() +
                '}';
    }
}
