package de.aw.evolution.domain;

import java.util.UUID;

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

    private final UUID id;
    private final GeneLocus locus;

    public Gene(GeneLocus locus, UUID id) {
        if(locus == null || id == null) {
            throw new IllegalArgumentException("Locus and id must be set");
        }
        this.locus = locus;
        this.id = id;
    }

    public GeneLocus getLocus() {
        return locus;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "id=" + id +
                ", locus=" + locus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gene)) return false;

        Gene gene = (Gene) o;

        if (!getLocus().equals(gene.getLocus())) return false;
        return id.equals(gene.id);

    }

    @Override
    public int hashCode() {
        int result = getLocus().hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
