import java.time.LocalDate;
import java.util.Objects;

class Experience implements Comparable<Experience> {
    LocalDate startDate, endDate;
    String position;
    Company company;

    public Experience(LocalDate startDate, LocalDate endDate, String position, Company company) throws InvalidDatesException {
        if (endDate != null && startDate.compareTo(endDate) > 0)
            throw new InvalidDatesException();
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.company = company;
    }

    @Override
    public int compareTo(Experience o) {
        if (endDate == null || o.endDate == null) {
            return company.name.compareTo(o.company.name);
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
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, position, company);
    }
}
