package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Couple;
import de.aw.evolution.domain.model.Genom;

/**
 * Durch Rekombination, die durch die Meiose bei der Keimzellenbildung und die Kernverschmelzung bei der Befruchtung erfolgt,
 * werden die Erbanlagen der Eltern neu kombiniert, so dass Nachkommen mit individuellen (einzigartigen) Kombinationen
 * von Erbanlagen entstehen.
 *
 * Bei der Rekombination bleiben die relativen Häufigkeiten der Erbanlagen in einer Population unverändert,
 * aber die (insbesondere phänotypische) Variabilität der Individuen in einer Population wird wirksam erhöht.
 *
 * Rekombination findet bei ungeschlechtlicher Fortpflanzung nicht statt und ist auch nicht wirksam zwischen
 * eineiigen Zwillingen, die aus einer gemeinsamen befruchteten Eizelle entstehen.
 *
 * @author armin.weisser
 */
public interface Recombination extends EvolutionaryFactor<Couple, Genom> {
}
