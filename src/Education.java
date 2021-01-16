import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Education implements Comparable<Education> {
    LocalDate startDate, endDate;
    String educationLevel, name;
    Double gpa;

    public Education(LocalDate startDate, LocalDate endDate, String educationLevel, String name, Double gpa) throws InvalidDatesException {
        if (endDate != null && startDate.compareTo(endDate) > 0)
            throw new InvalidDatesException();
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationLevel = educationLevel;
        this.name = name;
        this.gpa = gpa;
    }

    public Education(Test.MyEducation education) throws InvalidDatesException {
        this(
                LocalDate.parse(education.start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                education.end_date == null ? null : LocalDate.parse(education.end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                education.level,
                education.name,
                education.grade
        );
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

    @Override
    public String toString() {
        return  name + " " + educationLevel + " with GPA " + gpa + " between " +
                startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " and " +
                (endDate == null ? "now" : endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
