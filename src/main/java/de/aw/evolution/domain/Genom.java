package de.aw.evolution.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
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
public class Genom implements Iterable<Gene> {

    private final HashMap<GeneLocus, Gene> dna = new HashMap<>();

    public Genom(Collection<Gene> genes) {
        genes.forEach(this::addOrReplaceGen);
    }

    @Override
    public Iterator<Gene> iterator() {
        return dna.values().iterator();
    }

    public int size() {
        return dna.size();
    }

    /**
     * @return a real copy (with new Gene instances)
     */
    public Genom clone() {
        Set<Gene> copy = dna.values().stream().map(g -> g.clone()).collect(Collectors.toSet());
        return new Genom(copy);
    }

    public Optional<Gene> addOrReplaceGen(Gene newGene) {
        Optional<Gene> replacedGene = Optional.ofNullable(dna.get(newGene.getLocus()));
        dna.put(newGene.getLocus(), newGene);
        return replacedGene;
    }
}
