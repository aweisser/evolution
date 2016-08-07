package de.aw.evolution;

import de.aw.evolution.domain.Environment;
import de.aw.evolution.domain.Organism;
import de.aw.evolution.domain.Population;
import de.aw.evolution.domain.actors.EvolutionaryFactors;

import java.util.Collection;

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
 * @author armin.weisser
 */
public class Evolution {

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
            population.apply(evolutionaryFactors.getMutation());
            population.apply(evolutionaryFactors.getGeneticDrift());
            if(population.size() == 0) {
                throw new PopulationLostException();
            }
            Collection<Organism> weakest = population.getWeakest(0.25);
            population.killAll(weakest);
            if(population.size() < 100 ) {
                population.reproduce(evolutionaryFactors.getEvolutionaryFactorsForReproduction());
            }
        }
    }
}
