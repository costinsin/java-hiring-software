import java.util.Objects;

public class Constraint {
    Double lowerBound, upperBound;

    public Constraint(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean respectsConstraint(Double value) {
        if (lowerBound == null)
            lowerBound = Double.MIN_VALUE;
        if (upperBound == null)
            upperBound = Double.MAX_VALUE;
        if (value == null)
            return false;

        if (lowerBound <= value && value <= upperBound)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraint that = (Constraint) o;
        return Double.compare(that.lowerBound, lowerBound) == 0 &&
                Double.compare(that.upperBound, upperBound) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerBound, upperBound);
    }
}
