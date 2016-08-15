package de.aw.evolution.domain.model;

import java.util.HashSet;

/**
 * @author armin.weisser
 */
public class Group extends HashSet<Organism> {

    public static class EmptyGroup extends Group {

    }

}
