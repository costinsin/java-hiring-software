import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class Experience implements Comparable<Experience> {
    LocalDate startDate, endDate;
    String position;
    String companyName;

    public Experience(LocalDate startDate, LocalDate endDate, String position, String companyName) throws InvalidDatesException {
        if (endDate != null && startDate.compareTo(endDate) > 0)
            throw new InvalidDatesException();
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.companyName = companyName;
    }

    public Experience(Test.MyExperience experience) throws InvalidDatesException {
        this(
                LocalDate.parse(experience.start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                LocalDate.parse(experience.end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                experience.position,
                experience.company
        );
    }

    @Override
    public int compareTo(Experience o) {
        if (endDate == null || o.endDate == null) {
            return companyName.compareTo(o.companyName);
        } else {
            return -endDate.compareTo(o.endDate);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(position, that.position) &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, position, companyName);
    }

    @Override
    public String toString() {
        return position + " at " + companyName + " between " +
                startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " and " +
                (endDate == null ? "now" : endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
