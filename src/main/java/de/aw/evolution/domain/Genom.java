package de.aw.evolution.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Die gesamte Erbinformation einer Zelle wird Genom genannt.
 *
 * Als Genom, auch Erbgut eines Lebewesens oder eines Virus, bezeichnet man die Gesamtheit der
 * materiellen Träger der vererbbaren Informationen.
 *
 * @author armin.weisser
 */
public class Genom {

    private final Set<Gene> genes = new TreeSet<>(genelocusComparator());

    private Comparator<Gene> genelocusComparator() {
        return (g1, g2) -> g1.getLocus().compareTo(g2.getLocus());
    }

    public Genom(Collection<Gene> genes) {
        this.genes.addAll(genes);
    }

    public Set<Gene> getGenes() {
        return Collections.unmodifiableSet(genes);
    }

    public static Genom copyOf(Genom genom) {
        return new Genom(genom.getGenes());
    }

}
