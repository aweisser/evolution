package de.aw.evolution.domain.model;

/**
 * Die genetische Information ist der Nutzteil eines Gens.
 *
 * @author armin.weisser
 */
public abstract class GeneticInformation<T> {

    private T data;

    public GeneticInformation(T data) {
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneticInformation)) return false;

        GeneticInformation<?> that = (GeneticInformation<?>) o;

        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;

    }

    @Override
    public int hashCode() {
        return getData() != null ? getData().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getData() != null ? getData().toString() : null;
    }

    public abstract GeneticInformation<T> clone();

    public static class NoGeneticInformation extends GeneticInformation<Object> {
        public NoGeneticInformation() {
            super(null);
        }

        @Override
        public GeneticInformation<Object> clone() {
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof NoGeneticInformation;
        }
    }

    public static class SimpleGeneticInformation extends GeneticInformation<String> {

        public SimpleGeneticInformation(String data) {
            super(data);
        }

        @Override
        public GeneticInformation<String> clone() {
            return new SimpleGeneticInformation(getData());
        }
    }
}
