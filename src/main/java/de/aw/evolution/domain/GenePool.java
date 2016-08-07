package de.aw.evolution.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Der Genpool bezeichnet die Gesamtheit aller Genvariationen (Allele) einer Population.
 *
 * @author armin.weisser
 */
public class GenePool {

    private final Map<GeneLocus, Allel> allels = new HashMap<>();

    private GenePool() {

    }

    public boolean contains(Gene gene) {
        Allel allel = allels.get(gene.getLocus());
        return allel != null && allel.contains(gene);
    }

    public boolean containsAll(Set<Gene> genes) {
        return genes.stream().allMatch( g -> this.contains(g) );
    }

    public static GenePool create(Set<Organism> organisms) {
        GenePool genePool = new GenePool();
        organisms.forEach( o -> collectAlles(genePool, o));
        return genePool;
    }

    private static void collectAlles(GenePool genePool, Organism organism) {
        organism.getGenom().getGenes().forEach(g -> addGene(genePool.allels, g));
    }

    private static void addGene(Map<GeneLocus, Allel> allels, Gene gene) {
        if(!allels.containsKey(gene.getLocus())) {
            Set<Gene> geneSet = Stream.of(gene).collect(Collectors.toSet());
            allels.put(gene.getLocus(), new Allel(gene.getLocus(), geneSet));
        } else {
            allels.get(gene.getLocus()).addVariation(gene);
        }
    }
}
