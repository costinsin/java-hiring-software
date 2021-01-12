import java.time.LocalDate;
import java.util.Objects;

public class Education implements Comparable<Education> {
    LocalDate startDate, endDate;
    String educationLevel;
    Double gpa;

    public Education(LocalDate startDate, LocalDate endDate, String educationLevel, Double gpa) throws InvalidDatesException {
        if (endDate != null && startDate.compareTo(endDate) > 0)
            throw new InvalidDatesException();
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationLevel = educationLevel;
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Education o) {
        if (endDate == null || o.endDate == null) {
            return startDate.compareTo(o.startDate);
        } else {
            int comp = endDate.compareTo(o.endDate);
            if (comp == 0)
                comp = gpa.compareTo(o.gpa);
            return -comp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(startDate, education.startDate) &&
                Objects.equals(endDate, education.endDate) &&
                Objects.equals(educationLevel, education.educationLevel) &&
                Objects.equals(gpa, education.gpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, educationLevel, gpa);
    }
}
