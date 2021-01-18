import java.util.*;

public class Company implements Subject{
    String name;
    Manager manager;
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Recruiter> recruiters = new ArrayList<>();
    ArrayList<User> observers = new ArrayList<>();

    public Company() {
        departments.add(new DepartmentFactory().getDepartment("IT"));
        departments.add(new DepartmentFactory().getDepartment("Management"));
        departments.add(new DepartmentFactory().getDepartment("Marketing"));
        departments.add(new DepartmentFactory().getDepartment("Finance"));
    }

    public Company(String name) {
        this.name = name;
        departments.add(new DepartmentFactory().getDepartment("IT"));
        departments.add(new DepartmentFactory().getDepartment("Management"));
        departments.add(new DepartmentFactory().getDepartment("Marketing"));
        departments.add(new DepartmentFactory().getDepartment("Finance"));
    }

    public void add(Department department) {
        departments.add(department);
    }

    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        department.employees.add(employee);
    }

    public void remove(Employee employee) {
        for (Department department : departments) {
            department.employees.remove(employee);
        }
    }

    public void remove(Department department) {
        departments.remove(department);
    }

    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiters);
    }

    public void move(Department source, Department destination) {
        for (Employee employee : source.employees) {
            destination.add(employee);
            source.remove(employee);
        }
    }

    public void move(Employee employee, Department newDepartment) {
        this.remove(employee);
        newDepartment.add(employee);
    }

    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        for (Department department : departments) {
            if (department.employees.contains(employee))
                return true;
        }
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user) {
        Vector<Recruiter> bestMatch = new Vector<>();
        Recruiter result = null;
        int maxDepth = 0;

        for (Recruiter recruiter : recruiters) {
            int distance = user.getDegreeInFriendship(recruiter);
            if (distance > maxDepth) {
                maxDepth = distance;
                result = recruiter;
            } else if (distance == maxDepth) {
                result = result.compareTo(recruiter) > 0 ? result : recruiter;
            }
        }

        Collections.sort(bestMatch);

        return bestMatch.lastElement();
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> result = new ArrayList<>();

        for (Department department : departments) {
            result.addAll(department.jobs);
        }

        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(manager, company.manager) &&
                Objects.equals(departments, company.departments) &&
                Objects.equals(recruiters, company.recruiters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manager, departments, recruiters);
    }

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User user) {
        observers.remove(user);
    }

    @Override
    public void notifyAllObservers(Notification notification) {
        for (User observer : observers) {
            observer.update(notification);
        }
    }
}


interface Subject {
    void addObserver(User user);
    void removeObserver(User user);
    void notifyAllObservers(Notification notification);
}