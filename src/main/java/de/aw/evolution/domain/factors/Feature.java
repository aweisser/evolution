package de.aw.evolution.domain.factors;

/**
 * @author armin.weisser
 */
@FunctionalInterface
public interface Feature {
    Object execute(Object... input);
}
