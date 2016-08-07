package de.aw.evolution.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    private final Set<Gene> genes;

    public Genom(Collection<Gene> genes) {
        this.genes = genes.stream().collect(Collectors.toSet());
    }

    public Set<Gene> getGenes() {
        return Collections.unmodifiableSet(genes);
    }

    /**
     * @return a real copy (with new Gene instances)
     */
    public Genom clone() {
        Set<Gene> copy = getGenes().stream().map(g -> g.clone()).collect(Collectors.toSet());
        return new Genom(copy);
    }

}
