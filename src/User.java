import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        super();
        this.resume.informatrion.setFirstName(consumer.name.split(" ")[0]);
        this.resume.informatrion.setLastName(consumer.name.split(" ")[1]);
        this.resume.informatrion.setEmail(consumer.email);
        this.resume.informatrion.setPhoneNumber(consumer.phone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.resume.informatrion.setBirthDate(LocalDate.parse(consumer.date_of_birth, formatter));
        this.resume.informatrion.setGenre(consumer.genre);
        for (int i = 0; i < consumer.languages.size(); i++) {
            Informatrion.Language language = new Informatrion.Language(
                    consumer.languages.get(i),
                    consumer.languages_level.get(i)
            );
            this.resume.informatrion.getLanguages().add(language);
        }
        this.wishlist = consumer.interested_companies;
        this.notifications = new ArrayList<>();
        for (Test.MyEducation education : consumer.education) {
            this.resume.education.add(new Education(education));
        }
        for (Test.MyExperience experience : consumer.experience) {
            this.resume.experience.add(new Experience(experience));
        }
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
    public void update(Notification notification) {
        notifications.add(notification);
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