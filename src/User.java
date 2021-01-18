import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class User extends Consumer implements Observer {
    ArrayList<String> wishlist;
    ArrayList<Notification> notifications;

    public User() {
        super();
        this.wishlist = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public User(ArrayList<String> wishlist) {
        super();
        this.wishlist = wishlist;
        this.notifications = new ArrayList<>();
    }

    public User(Test.MyConsumer consumer) throws InvalidDatesException {
        super(consumer);
        this.wishlist = consumer.interested_companies;
        this.notifications = new ArrayList<>();
    }

    public Employee convert() {
        return new Employee(this);
    }
    public Employee convert(String companyName, Double salary) {
        return new Employee(this, companyName, salary);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(wishlist, user.wishlist) &&
                Objects.equals(notifications, user.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wishlist, notifications);
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

interface Observer {
    void update(Notification notification);
}

class Notification {
    String text;
    LocalDateTime dateTime;

    public Notification(String text) {
        this.text = text;
        this.dateTime = LocalDateTime.now();
    }
}