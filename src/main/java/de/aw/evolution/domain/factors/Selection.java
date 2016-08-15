package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.model.Group;

/**
 * Selektion ist einer der Evolutionsfaktoren.
 *
 *
 * Man unterscheidet
 *
 * - natürliche Selektion
 *      Natürliche Selektion tritt auf, weil Individuen mit Merkmalen, die für das Überleben und die Fortpflanzung
 *      vorteilhaft sind, mehr Nachwuchs produzieren können als Individuen ohne diese Merkmale.
 *      Wenn ein Merkmal die „evolutionäre Fitness“ von Individuen erhöht, dann werden diese Individuen mit höherer
 *      Wahrscheinlichkeit überleben und reproduzieren, als andere Individuen dieser Population und daher mehr Kopien
 *      ihrer Merkmale an die nächste Generation weitergeben (survival of the fittest).
 *      Umgekehrt wird ein Fitnessverlust durch ein nachteiliges Merkmal dazu führen, dass dieses Merkmal seltener wird.
 *
 * - sexuelle Selektion
 *      Ein Spezialfall der natürlichen Selektion ist die sexuelle Selektion: Die Selektion auf Merkmale, deren Präsenz
 *      direkt mit dem Kopulationserfolg durch bevorzugte Partnerwahl korreliert ist.
 *
 * - künstliche Selektion
 *      Tritt in einer vom Menschen gesteuerten Zuchtwahl auf. Sie steigert den Fortpflanzungserfolg jener Individuen,
 *      die die vom Züchter geförderten Eigenschaften besitzen.
 *
 * TODO Man könnte das in weitere Unterklassen differenzieren.
 *
 * @author armin.weisser
 */
public interface Selection extends EvolutionaryFactor<Group, Group> {

    static Selection all() {
        return group -> group;
    }
}
