package de.aw.evolution.domain.model;

/**
 * Als Gen wird meist ein Abschnitt auf der DNA bezeichnet, der die Grundinformationen zur Herstellung
 * einer biologisch aktiven RNA enthält.
 *
 * Allgemein werden Gene auch als Erbanlage oder Erbfaktor bezeichnet, da sie die Träger von Erbinformation sind,
 * die durch Reproduktion an Nachkommen weitergegeben wird.
 *
 * TODO Den Aufbau eines Gen-Objektes könnte man noch viel detaillierter abbilden
 *
 * @author armin.weisser
 */
public class Gene {

    // TODO is a GeneLocus part of a Gene? Does a Gene have to know where it's located in a Chromosom?
    private final GeneLocus locus;
    private final GeneticInformation geneticInformation;

    public Gene(GeneLocus locus) {
        this(locus, null);
    }

    public Gene(GeneLocus locus, GeneticInformation geneticInformation) {
        if(locus == null) {
            throw new IllegalArgumentException("Locus must be set");
        }
        if(geneticInformation == null) {
            geneticInformation = new GeneticInformation.NoGeneticInformation();
        }
        this.locus = locus;
        this.geneticInformation = geneticInformation;
    }

    public GeneLocus getLocus() {
        return locus;
    }

    public GeneticInformation getGeneticInformation() {
        return geneticInformation;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "locus=" + locus +
                ", geneticInformation=" + geneticInformation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gene)) return false;

        Gene gene = (Gene) o;

        if (!getLocus().equals(gene.getLocus())) return false;
        return getGeneticInformation().equals(gene.getGeneticInformation());

    }

    @Override
    public int hashCode() {
        int result = getLocus().hashCode();
        result = 31 * result + getGeneticInformation().hashCode();
        return result;
    }

    /**
     * @return a new Gene instance with new id but same Locus and genetic information
     */
    public Gene clone() {
        return new Gene(getLocus(), getGeneticInformation().clone());
    }
}
