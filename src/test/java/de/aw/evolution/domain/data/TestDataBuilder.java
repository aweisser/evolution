package de.aw.evolution.domain.data;

import de.aw.evolution.domain.Gene;
import de.aw.evolution.domain.GeneLocus;
import de.aw.evolution.domain.Generation;
import de.aw.evolution.domain.Genom;
import de.aw.evolution.domain.Organism;
import de.aw.evolution.domain.Phenotype;
import de.aw.evolution.domain.actors.EvolutionaryFactorsForReproduction;
import de.aw.evolution.domain.actors.Feature;
import de.aw.evolution.domain.actors.PartnerSelection;
import de.aw.evolution.domain.actors.Recombination;
import de.aw.evolution.domain.actors.Selection;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author armin.weisser
 */
public abstract class TestDataBuilder {

    public static Organism anOrganismn(Generation generation, Genom genom, Phenotype phenotype) {
        return new Organism(generation, genom, phenotype);
    }

    public static Organism anOrganismn() {
        return new Organism(aGeneration(), aGenom(), aPhenotype());
    }

    private static Phenotype aPhenotype() {
        return new Phenotype();
    }

    public static Gene aGeneAtLocus(int position) {
        return new Gene(aGeneLocus(position), UUID.randomUUID());
    }

    public static GeneLocus aGeneLocus(int position) {
        return new GeneLocus(position);
    }

    public static <T> Set<T> asSet(T... things) {
        return Stream.of(things).collect(Collectors.toSet());
    }

    public static <T> List<T> asList(T... things) {
        return Stream.of(things).collect(Collectors.toList());
    }

    public static Generation aGeneration() {
        return Generation.createFirstGeneration();
    }

    public static Genom aGenom() {
        Gene brownHairGene = aGeneAtLocus(1);
        Gene blueEyesGene = aGeneAtLocus(2);
        return new Genom(asSet(brownHairGene, blueEyesGene));
    }

    public static Feature aFeature() {
        return new TestFeature();
    }

    public static EvolutionaryFactorsForReproduction defaultEvolutionaryFactorsForReproduction() {
        Selection selection = organisms -> organisms;
        PartnerSelection partnerSelection = organisms -> Optional.of(organisms.iterator().next());
        Recombination recombination = couple -> couple.getMother();
        return new EvolutionaryFactorsForReproduction(selection, partnerSelection, recombination);
    }
}
