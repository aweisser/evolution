package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Genom;

/**
 *  Als Mutation wird in der Biologie eine dauerhafte Veränderung des Erbgutes (Genom) bezeichnet.
 *
 *  Durch Mutationen, spontane Veränderungen der Basensequenzen der DNA, entstehen fortwährend neue Erbanlagen.
 *  Findet eine Mutation in einer Zelle statt, aus der später Keimzellen hervorgehen, so wird die veränderte Erbanlage
 *  über die befruchtete Eizelle auf die Nachkommen übertragen und verändert damit den Genpool der Population.
 *
 *  Die neue Erbanlage führt zu Merkmalsausprägungen, die bisher in der Population nicht vorkamen.
 *  Ob es zu einer nachhaltigen Veränderung des Genpools kommt, hängt entscheidend davon ab,
 *  wie die Selektion auf die neue Merkmalsausprägung wirkt.
 *
 *  Erbanlagen, die zu nachteiligen Merkmalsausprägungen führen, verschwinden wieder aus dem Genpool oder bleiben selten.
 *
 * @author armin.weisser
 */
public interface Mutation extends EvolutionaryFactor<Genom, Genom> {

    static Mutation none() {
        return genom -> genom;
    }

}
