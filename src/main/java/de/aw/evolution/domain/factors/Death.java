package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Group;

/**
 * @author armin.weisser
 */
public interface Death extends EvolutionaryFactor<Group, Group> {

    static Death none() {
        return group -> new Group.EmptyGroup();
    }

}
