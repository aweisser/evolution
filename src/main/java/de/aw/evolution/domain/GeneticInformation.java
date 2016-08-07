package de.aw.evolution.domain;

/**
 * Die genetische Information ist der Nutzteil eines Gens.
 *
 * @author armin.weisser
 */
public abstract class GeneticInformation<T> {

    private final T data;

    public GeneticInformation(T data) {
        this.data = data;
    }

    public abstract GeneticInformation<T> clone();

    public static class NoGeneticInformation extends GeneticInformation<String> {
        public NoGeneticInformation() {
            super("");
        }

        @Override
        public GeneticInformation<String> clone() {
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof NoGeneticInformation;
        }
    }
}
