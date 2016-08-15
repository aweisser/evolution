package de.aw.evolution.domain.model;

/**
 *
 * @author armin.weisser
 */
public class Fitness implements Comparable<Fitness> {

    private Double value;

    /**
     * @param value The greater the fitter.
     */
    public Fitness(double value) {
        this();
        this.value = value;
    }

    private Fitness() {

    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fitness)) return false;

        Fitness fitness = (Fitness) o;

        return value.equals(fitness.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Fitness other) {
        return this.value.compareTo(other.value);
    }

    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public String toString() {
        return value == null ? "empty" : value.toString();
    }

    public static class EmptyFitness extends Fitness {

        @Override
        public Double getValue() {
            throw new UnsupportedOperationException("An EmptyFitness does not have a value");
        }

        @Override
        public int compareTo(Fitness other) {
            throw new UnsupportedOperationException("You can't compare an EmptyFitness.");
        }
    }


}
