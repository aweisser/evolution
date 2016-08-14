package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Group;
import de.aw.evolution.domain.Organism;

import java.util.Optional;

/**
 *
 * @author armin.weisser
 */
public interface PartnerSelection extends EvolutionaryFactor<Group, Optional<Organism>> {

    static PartnerSelection any() {
        return group -> {
            if(group.iterator().hasNext()) {
                return Optional.of(group.iterator().next());
            }
            return Optional.empty();
        };
    }
}
