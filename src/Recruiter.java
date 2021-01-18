import java.util.Objects;

public class Recruiter extends Employee implements Comparable<Recruiter> {
    double rating;

    public Recruiter(String companyName, Double salary) {
        super(companyName, salary);
        rating = 5;
    }

    public Recruiter(Test.MyConsumer consumer) throws InvalidDatesException {
        super(consumer);
        rating = 5;
    }

    public int evaluate(Job job, User user) {
        double score = user.getTotalScore();
        Company currentCompany = null;
        for (Company i : Application.getInstance().companies) {
            if (this.companyName.equals(i.name)) {
                currentCompany = i;
                break;
            }
        }
        assert currentCompany != null;
        currentCompany.manager.requests.add(new Request<>(job, user, this, score));
        rating += 0.1;
        return (int) (rating * user.getTotalScore());
    }

    @Override
    public int compareTo(Recruiter o) {
        /*
        if (rating > o.rating)
            return 1;
        else if (rating < o.rating)
            return -1;
        return 0;
        */
        return Double.compare(rating, o.rating);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recruiter recruiter = (Recruiter) o;
        return Double.compare(recruiter.rating, rating) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating);
    }
}
