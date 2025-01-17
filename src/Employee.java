import java.util.Objects;

public class Employee extends Consumer {
    String companyName;
    Double salary;

    public Employee(User user) {
        this.resume = user.resume;
        this.friends = user.friends;
        this.companyName = null;
        this.salary = null;
    }

    public Employee(User user, String companyName, Double salary) {
        this.resume = user.resume;
        this.friends = user.friends;
        this.companyName = companyName;
        this.salary = salary;
    }

    public Employee(String companyName, Double salary) {
        super();
        this.companyName = companyName;
        this.salary = salary;
    }

    public Employee(Test.MyConsumer consumer) throws InvalidDatesException {
        super(consumer);
        this.companyName = consumer.findCurrentExperience().company;
        this.salary = consumer.salary;
    }

    public Experience getCurrentExperience() {
        for (Experience experience : this.resume.experience) {
            if (experience.endDate == null)
                return experience;
        }
        return null;
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
        Employee employee = (Employee) o;
        return Objects.equals(companyName, employee.companyName) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, salary);
    }
}
