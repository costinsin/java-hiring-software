import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Department {
    ArrayList<Employee> employees;
    ArrayList<Job> jobs;

    public abstract double getTotalSalaryBudget();

    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public void add(Job job) {
        jobs.add(job);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(employees, that.employees) &&
                Objects.equals(jobs, that.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees, jobs);
    }
}

class IT extends Department {

    @Override
    public double getTotalSalaryBudget() {
        double result = 0;

        for (Employee employee : employees) {
            result += employee.salary;
        }
        return result;
    }
}

class Management extends Department {

    @Override
    public double getTotalSalaryBudget() {
        double result = 0;

        for (Employee employee : employees) {
            result += employee.salary;
        }
        return result * 16 / 100;
    }
}

class Marketing extends Department {

    @Override
    public double getTotalSalaryBudget() {
        double result = 0;

        for (Employee employee : employees) {
            double salary = employee.salary;
            if (employee.salary > 5000)
                salary *= 10.0 / 100;
            else if (employee.salary > 3000)
                salary *= 16.0 / 100;
            result += salary;
        }
        return result;
    }
}

class Finance extends Department {

    @Override
    public double getTotalSalaryBudget() {
        double result = 0;

        for (Employee employee : employees) {
            double salary = employee.salary;
            if (Period.between(employee.getCurrentExperience().startDate, LocalDate.now()).getYears() >= 1)
                salary *= 16.0 / 100;
            else
                salary *= 10.0 / 100;
            result += salary;
        }
        return result;
    }
}
