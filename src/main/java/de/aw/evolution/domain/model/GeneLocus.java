package de.aw.evolution.domain.model;

/**
 * Genlocus heißt in der Genetik die physische Position eines Gens im Genom, der Genort.
 *
 * @author armin.weisser
 */
public class GeneLocus implements Comparable<GeneLocus> {

    private final Integer position;

    public GeneLocus(int position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public int compareTo(GeneLocus other) {
        return this.getPosition().compareTo(other.getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneLocus)) return false;

        GeneLocus geneLocus = (GeneLocus) o;

        return getPosition().equals(geneLocus.getPosition());

    }

    @Override
    public String toString() {
        return "GeneLocus{" +
                "position=" + position +
                '}';
    }

    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }
}
