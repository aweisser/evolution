package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Phenotype;

/**
 * @author armin.weisser
 */
@FunctionalInterface
public interface PhenotypeCreator<FROM> {
    Phenotype createPhenotypeFrom(FROM from);
}
