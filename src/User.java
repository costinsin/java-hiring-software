import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User extends Consumer {
    ArrayList<String> wishlist;

    public User() {
        this.wishlist = new ArrayList<>();
    }

    public User(ArrayList<String> wishlist) {
        this.wishlist = wishlist;
    }

    public Employee convert() {
        return new Employee(this);
    }

    public Double getTotalScore() {
        int expYears = 0;
        for (Experience experience : resume.experience) {
            Period period;
            if (experience.endDate == null)
                period = Period.between(experience.startDate, LocalDate.now());
            else
                period = Period.between(experience.startDate, experience.endDate);

            if (period.equals(Period.ofYears(period.getYears())))
                expYears += period.getYears();
            else
                expYears += period.getYears() + 1;
        }
        return expYears * 1.5 + this.meanGPA();
    }
}
