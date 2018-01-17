package app.utils;

import java.util.Objects;

public class Triad <F, S, T> {
    private F first;
    private S second;
    private T third;

    public Triad() {
        this(null, null, null);
    }

    public Triad(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public T getThird() {
        return third;
    }

    public void setThird(T third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triad<?, ?, ?> triad = (Triad<?, ?, ?>) o;
        return Objects.equals(first, triad.first) &&
                Objects.equals(second, triad.second) &&
                Objects.equals(third, triad.third);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "Triad{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
