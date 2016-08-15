package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Population;

/**
 * Unter Gendrift versteht man eine zufallsbedingte Änderung des Genpools.
 *
 * Sie ist in kleinen Populationen wirksamer als in großen.
 * So kann zum Beispiel bei einer Naturkatastrophe oder einer Seuche eine Gruppe von Trägern bestimmter Merkmale
 * plötzlich aussterben. Es breitet sich der überlebende Teil der Population mit etwas anderer genetischer Zusammensetzung aus,
 * beim zufälligen Überleben von Individuen mit nachteiligen Erbanlagen breiten sich sogar diese aus.
 *
 * Ein weiteres Beispiel für Gendrift ist die Besiedlung eines neuen Lebensraums durch eine kleine Gründerpopulation.
 * Die neue Population weist die Häufigkeitsverteilung der Erbanlagen der Gründerpopulation auf,
 * die sich zufallsbedingt von der der Stammpopulation unterscheiden.
 *
 * DEV-Note:
 * Man kann das nicht wirklich als eine direkte Änderung des GenePool modelieren.
 * Anstelle desssen modellieren wir es als direkte und eher zufällige Änderung an der Population.
 * So können wir das plötzliche Aussterben oder ein neues Environment simulieren.
 * Die Änderung am GenePool ist dann implizit.
 *
 * @author armin.weisser
 */
public interface GeneticDrift extends EvolutionaryFactor<Population, Population> {

    static GeneticDrift none() {
        return population -> population;
    }
}
