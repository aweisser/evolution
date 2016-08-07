package de.aw.evolution.domain.factors;

import de.aw.evolution.domain.Generation;

/**
 * Evolution ist die allmähliche Veränderung der vererbbaren Merkmale einer Population von Lebewesen von
 * Generation zu Generation.
 *
 * @author armin.weisser
 */
public interface Evolution {

    Generation apply(Generation generation);

}
