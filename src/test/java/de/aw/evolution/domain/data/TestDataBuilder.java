package de.aw.evolution.domain.data;

import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.EnvironmentalFactor;
import de.aw.evolution.domain.factors.Feature;
import de.aw.evolution.domain.factors.PartnerSelection;
import de.aw.evolution.domain.factors.PhenotypeCreator;
import de.aw.evolution.domain.factors.Recombination;
import de.aw.evolution.domain.factors.Reproduction;
import de.aw.evolution.domain.factors.Selection;
import de.aw.evolution.domain.model.Environment;
import de.aw.evolution.domain.model.Fitness;
import de.aw.evolution.domain.model.Gene;
import de.aw.evolution.domain.model.GeneLocus;
import de.aw.evolution.domain.model.Generation;
import de.aw.evolution.domain.model.GeneticInformation;
import de.aw.evolution.domain.model.Genom;
import de.aw.evolution.domain.model.Organism;
import de.aw.evolution.domain.model.Phenotype;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
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
        return anOrganism(anEmptyGeneration());
    }

    public static Organism anOrganismWith(Fitness fitness, Generation generation) {
        Organism organism = anOrganism(generation);
        organism.setFitness(fitness);
        return organism;
    }

    public static Organism anOrganism(Generation generation) {
        return new Organism(generation, aGenom(), aPhenotype());
    }

    public static Organism anOrganism(Generation generation, Supplier<Genom> genomSupplier, PhenotypeCreator<Genom> phenotypeCreator) {
        return new Organism(generation, genomSupplier.get(), phenotypeCreator);
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
        Generation generation = anEmptyGeneration();
        for(int i=0;i<size;i++) {
            generation.add(anOrganism(generation));
        }
        return generation;
    }

    public static Generation anEmptyGeneration() {
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

    public static Reproduction defaultReproduction() {
        Selection selection = organisms -> organisms;
        PartnerSelection partnerSelection = organisms -> organisms.iterator().hasNext() ? Optional.of(organisms.iterator().next()) : Optional.empty();
        Recombination recombination = couple -> couple.getMother();
        return new Reproduction(selection, partnerSelection, recombination, aPhenotypeCreator());
    }

    public static PhenotypeCreator<Genom> aPhenotypeCreator() {
        return genes -> aPhenotype();
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
