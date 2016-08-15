package de.aw.evolution;

import de.aw.evolution.domain.factors.Death;
import de.aw.evolution.domain.factors.EnvironmentalFactor;
import de.aw.evolution.domain.factors.Feature;
import de.aw.evolution.domain.factors.GeneticDrift;
import de.aw.evolution.domain.factors.Mutation;
import de.aw.evolution.domain.factors.PartnerSelection;
import de.aw.evolution.domain.factors.PhenotypeCreator;
import de.aw.evolution.domain.factors.Recombination;
import de.aw.evolution.domain.factors.Selection;
import de.aw.evolution.domain.model.Environment;
import de.aw.evolution.domain.model.Fitness;
import de.aw.evolution.domain.model.Gene;
import de.aw.evolution.domain.model.GeneLocus;
import de.aw.evolution.domain.model.Generation;
import de.aw.evolution.domain.model.GeneticInformation;
import de.aw.evolution.domain.model.Genom;
import de.aw.evolution.domain.model.Phenotype;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import static de.aw.evolution.domain.data.TestDataBuilder.aGeneAtLocus;
import static de.aw.evolution.domain.data.TestDataBuilder.aGenom;
import static de.aw.evolution.domain.data.TestDataBuilder.anOrganism;


/**
 * Im  Experiment soll eine wahllose Zeichenkette (als initiales Genom) in ein "sinnvolles" Wort verwandelt werden.
 * Um den Lösungsraum klein ist das einzige sinnvolle Wort "Evolution".
 *
 *
 * @author armin.weisser
 */
@Ignore
public class EvolutionExperiment1 extends EvolutionFactory {

    private static final String MEANINGFULL_WORD = "Evolution";

    @Test
    public void startEvolution() throws PopulationLostException {
        get().start();
    }

    @Override
    protected Environment createEnvironment() {
        Environment environment = new Environment();
        environment.put(createMeaningFullWord(), 1);
        return environment;
    }

    private EnvironmentalFactor createMeaningFullWord() {
        return organism -> {
            Feature speakFeature = organism.getPhenotype().iterator().next();
            String word = (String) speakFeature.execute();
            double jaroWinklerDistance = StringUtils.getJaroWinklerDistance(word, MEANINGFULL_WORD);
            return new Fitness(jaroWinklerDistance);
        };
    }

    @Override
    protected Generation createFirstGeneration() {
        Generation firstGeneration = Generation.createFirstGeneration();
        for(int i=0;i<100;i++) {
            String randomDNA = RandomStringUtils.randomAlphabetic(MEANINGFULL_WORD.length());
            Gene gene = aGeneAtLocus(1, randomDNA);
            anOrganism(firstGeneration, () -> aGenom(gene), createPhenotypeCreator());
        }
        return firstGeneration;
    }

    @Override
    protected PhenotypeCreator<Genom> createPhenotypeCreator() {
        return genom -> createPhenotype(genom);
    }

    private Phenotype createPhenotype(Genom genom) {
        Phenotype features = new Phenotype();
        features.add(createSpeakFeature(genom));
        return features;
    }

    private Feature createSpeakFeature(Genom genom) {
        return input -> {
            Gene gene = genom.iterator().next();
            GeneticInformation geneticInformation = gene.getGeneticInformation();
            return geneticInformation.getData();
        };
    }

    @Override
    protected Recombination createRecombination() {
        return couple -> {
            GeneLocus locusOne = new GeneLocus(1);
            String dnaMother = couple.getMother().getGeneticInformationAt(locusOne, String.class);
            String dnaFather = couple.getFather().getGeneticInformationAt(locusOne, String.class);
            String childDNA = "";
            for(int i=0;i<dnaFather.length();i++) {
                childDNA += ((i%2)==0) ? dnaFather.charAt(i) : dnaMother.charAt(i);
            }
            Gene childGene = new Gene(locusOne, new GeneticInformation.SimpleGeneticInformation(childDNA));
            return new Genom(childGene);
        };
    }

    @Override
    protected Selection createSelection() {
        return Selection.all();
    }

    @Override
    protected PartnerSelection createPartnerSelection() {
       return PartnerSelection.any();
    }

    @Override
    protected GeneticDrift createGeneticDrift() {
        return GeneticDrift.none();
    }

    @Override
    protected Mutation createMutation() {
        return Mutation.none();
    }

    @Override
    protected Death createDeath() {
        return Death.forWeakest(0.25f);
    }

}
