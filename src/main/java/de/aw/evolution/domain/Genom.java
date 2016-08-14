package de.aw.evolution.domain;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Als Genom, auch Erbgut eines Lebewesens oder eines Virus, bezeichnet man die Gesamtheit der materiellen Träger der
 * vererbbaren Informationen einer Zelle.
 * Im abstrakten Sinn versteht man darunter auch die Gesamtheit der vererbbaren Informationen.
 *
 * Der Genotyp (griech. genos „Gattung, Geschlecht“ und typos „Abbild, Muster“) oder das Erbbild eines Organismus
 * repräsentiert seine exakte genetische Ausstattung, also den individuellen Satz von Genen,
 * den er im Zellkern in sich trägt und der somit seinen morphologischen und physiologischen Phänotyp bestimmt.
 *
 * Der Begriff „Genotyp“ bezieht sich also auf die vollständige Kombination aller Allele / aller Loci eines Organismus.
 *
 * Das Genom ist vereinfacht gesagt eine Art Bibliothek, in der die gesammten Erbinformationen liegen.
 * Die im Genom enthalteten Chromosomen kann man sich als Bücherregale in dieser Bibliothek vorstellen.
 * während die Gene die Bücher sind und die DNA der Text in den Büchern.
 * Der GenLocus ist dann die Position im Regal, an dem das Buch zu finden ist.
 *
 * @author armin.weisser
 */
public class Genom implements Iterable<Gene> {

    // TODO Streng genommen ist ein Genom eine Menge von Chromosomen auf denen die Gene an einem bestimmen Loki liegen
    // Zumindest haben die meisten Organismen mehrere Chromosomen.
    // TODO Welche Rolle spielen Chromosomen bei der Fortpflanzung?
    private final Chromosom chromosom = new Chromosom();

    public Genom(Gene... genes) {
        this(Stream.of(genes).collect(Collectors.toSet()));
    }

    public Genom(Iterable<Gene> genes) {
        genes.forEach(this::addOrReplaceGen);
    }

    @Override
    public Iterator<Gene> iterator() {
        return chromosom.values().iterator();
    }

    public int size() {
        return chromosom.size();
    }

    /**
     * @return a real copy (with new Gene instances)
     */
    public Genom clone() {
        Set<Gene> copy = chromosom.values().stream().map(g -> g.clone()).collect(Collectors.toSet());
        return new Genom(copy);
    }

    public Optional<Gene> addOrReplaceGen(Gene newGene) {
        Optional<Gene> replacedGene = Optional.ofNullable(chromosom.get(newGene.getLocus()));
        chromosom.put(newGene.getLocus(), newGene);
        return replacedGene;
    }

    public <T> T getGeneticInformationAt(GeneLocus locus, Class<T> clazz) {
        return (T) chromosom.get(locus).getGeneticInformation().getData();
    }
}
