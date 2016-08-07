package de.aw.evolution.domain;

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

    public static class EmptyFitness extends Fitness {
        @Override
        public int compareTo(Fitness other) {
            throw new UnsupportedOperationException("You can't compare an EmptyFitness.");
        }
    }

}
