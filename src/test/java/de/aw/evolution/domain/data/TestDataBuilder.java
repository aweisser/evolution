package de.aw.evolution.domain.data;

import de.aw.evolution.domain.Environment;
import de.aw.evolution.domain.Fitness;
import de.aw.evolution.domain.Gene;
import de.aw.evolution.domain.GeneLocus;
import de.aw.evolution.domain.Generation;
import de.aw.evolution.domain.GeneticInformation;
import de.aw.evolution.domain.Genom;
import de.aw.evolution.domain.Organism;
import de.aw.evolution.domain.Phenotype;
import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.EnvironmentalFactor;
import de.aw.evolution.domain.factors.Feature;
import de.aw.evolution.domain.factors.PartnerSelection;
import de.aw.evolution.domain.factors.Recombination;
import de.aw.evolution.domain.factors.Reproduction;
import de.aw.evolution.domain.factors.Selection;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author armin.weisser
 */
public abstract class TestDataBuilder {


    public static Organism anOrganism(Generation generation, Genom genom) {
        return new Organism(generation, genom);
    }

    public static Organism anOrganism() {
        return anOrganism(aGeneration());
    }

    public static Organism anOrganism(Generation generation) {
        return new Organism(generation, aGenom(), aPhenotype());
    }

    private static Phenotype aPhenotype() {
        return new Phenotype();
    }

    public static Gene aGeneAtLocus(int position) {
        return new Gene(aGeneLocus(position));
    }

    public static Gene aGeneAtLocus(int position, String data)
    {
        return new Gene(aGeneLocus(position), aGeneticInformation(data));
    }

    public static Gene aGeneAtLocus(int position, GeneticInformation geneticInformation)
    {
        return new Gene(aGeneLocus(position), geneticInformation);
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

    public static Generation aGenerationOfSize(Integer size) {
        Generation generation = aGeneration();
        for(int i=0;i<size;i++) {
            generation.add(anOrganism(generation));
        }
        return generation;
    }

    public static Generation aGeneration() {
        return Generation.createFirstGeneration();
    }

    public static Genom aGenom() {
        Gene brownHairGene = aGeneAtLocus(1);
        Gene blueEyesGene = aGeneAtLocus(2);
        return aGenom(brownHairGene, blueEyesGene);
    }

    public static Genom aGenom(Gene... genes) {
        return new Genom(asSet(genes));
    }

    public static Feature aFeature() {
        return new TestFeature();
    }

    public static Reproduction defaultEvolutionaryFactorsForReproduction() {
        Selection selection = organisms -> organisms;
        PartnerSelection partnerSelection = organisms -> Optional.of(organisms.iterator().next());
        Recombination recombination = couple -> couple.getMother();
        return new Reproduction(selection, partnerSelection, recombination);
    }

    public static Fitness aFitnessOf(double value) {
        return new Fitness(value);
    }

    public static Environment anEnvironment() {
        Environment environment = new Environment();
        environment.put(anEnvironmentalFactorWith(aFitnessOf(1)), 1);
        environment.put(anEnvironmentalFactorWith(aFitnessOf(0.5)), 10);
        return environment;
    }

    public static EnvironmentalFactor anEnvironmentalFactorWith(Fitness fitness) {
        return organism -> fitness;
    }

    public static Death aGenocide() {
        return organisms -> organisms;
    }

    public static GeneticInformation<String> aGeneticInformation(String data) {
        return new GeneticInformation.SimpleGeneticInformation(data);
    }
}
