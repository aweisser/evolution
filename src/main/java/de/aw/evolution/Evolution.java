package de.aw.evolution;

import de.aw.evolution.domain.factors.EvolutionaryFactors;
import de.aw.evolution.domain.model.Environment;
import de.aw.evolution.domain.model.Population;

import java.util.logging.Logger;

/**
 *
 * Evolution ist die allmähliche Veränderung der vererbbaren Merkmale einer Population von Lebewesen
 * von Generation zu Generation.
 *
 * Die Merkmale der Lebewesen sind in Form von Genen codiert, die bei der Fortpflanzung kopiert und an die Nachkommen
 * weitergegeben werden. Durch Mutationen entstehen unterschiedliche Varianten (Allele) dieser Gene, die zur Entstehung
 * veränderter oder neuer Merkmale führen können. Diese Varianten sowie Rekombinationen führen zu erblich bedingten
 * Unterschieden (Genetische Variabilität) zwischen Individuen. Evolution findet statt, wenn sich die Häufigkeit
 * bestimmter Allele in einer Population (die Allelfrequenz im Genpool) ändert und die entsprechenden Merkmale in der
 * Population dadurch seltener oder häufiger werden. Dies geschieht entweder durch natürliche Selektion
 * (unterschiedliche Überlebens- und Reproduktionsrate aufgrund dieser Merkmale), durch sexuelle Selektion oder
 * zufällig durch Gendrift.
 *
 *
 *
 * @author armin.weisser
 */
public class Evolution {

    private static Logger logger = Logger.getLogger("Evolution");

    private final Population population;
    private final Environment environment;
    private final EvolutionaryFactors evolutionaryFactors;

    public Evolution(Population population, Environment environment, EvolutionaryFactors evolutionaryFactors) {
        this.population = population;
        this.environment = environment;
        this.evolutionaryFactors = evolutionaryFactors;
    }

    public void start() throws PopulationLostException {
        while(true) {
            population.apply(environment);
            logger.info(population.toString());
            population.apply(evolutionaryFactors.death());
            if(population.size() == 0) {
                throw new PopulationLostException();
            }
            population.apply(evolutionaryFactors.reproduction());
            population.apply(evolutionaryFactors.mutation());
            population.apply(evolutionaryFactors.geneticDrift());
        }
    }
}
