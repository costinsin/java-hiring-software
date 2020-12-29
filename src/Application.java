import java.awt.print.Book;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Application {
    ArrayList<Company> companies;
    ArrayList<User> users;
    private static Application instance;

    private Application() {
        this.companies = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static Application getInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }


    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(String name) {
        for (Company company : companies) {
            if (company.name.equals(name))
                return company;
        }
        return null;
    }

    public void add(Company company) {
        companies.add(company);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean remove(Company company) {
        return companies.remove(company);
    }

    public boolean remove(User user) {
        return users.remove(user);
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> result = new ArrayList<>();
        for (Company company : this.companies) {
            if (companies.contains(company.name)) {
                result.addAll(company.getJobs());
            }
        }
        return result;
    }
}

abstract class Consumer {
    Resume resume;
    ArrayList<Consumer> friends;

    public Consumer() {
        resume = new Resume();
        friends = new ArrayList<>();
    }

    public void add(Education education) {
        resume.education.add(education);
    }

    public void add(Experience experience) {
        resume.experience.add(experience);
    }

    public void add(Consumer consumer) {
        friends.add(consumer);
    }

    public int getDegreeInFriendship(Consumer consumer) {
        Queue<Consumer> queue = new LinkedList<>();
        HashMap<Consumer, Integer> visitedSet = new HashMap<>();

        queue.add(this);
        visitedSet.put(this, 0);
        while (!queue.isEmpty()) {
            Consumer current = queue.poll();

            if (current.equals(consumer)) {
                return visitedSet.get(current);
            }

            for (Consumer i : current.friends) {
                if (!visitedSet.containsKey(i)) {
                    queue.add(i);
                    visitedSet.put(i, visitedSet.get(current) + 1);
                }
            }
        }

        return -1;
    }

    public void remove(Consumer consumer) {
        friends.remove(consumer);
    }

    public Integer getGraduationYear() {
        return resume.education.last().endDate.getYear();
    }

    public Double meanGPA() {
        double sum = 0f;
        int count = 0;
        for (Education education : resume.education) {
            if (education.gpa != null) {
                sum += education.gpa;
                count++;
            }
        }
        return sum / count;
    }

    public static class Resume {
        Informatrion informatrion;
        TreeSet<Education> education;
        TreeSet<Experience> experience;

        public Resume() {
            informatrion = new Informatrion();
            education = new TreeSet<>();
            experience = new TreeSet<>();
        }
    }
}

class Informatrion {
    private String lastName, firstName, email, phoneNumber, sex;
    private Date birthDate;
    private ArrayList<Language> languages;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    public static class Language {
        private String languageName, level;

        public Language(String languageName, String level) {
            this.languageName = languageName;
            this.level = level;
        }

        public String getLanguageName() {
            return languageName;
        }

        public void setLanguageName(String languageName) {
            this.languageName = languageName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}

class Education implements Comparable<Education> {
    LocalDate startDate, endDate;
    String educationLevel;
    Double gpa;

    public Education(LocalDate startDate, LocalDate endDate, String educationLevel, Double gpa) throws InvalidDatesException {
        if (endDate != null && startDate.compareTo(endDate) > 0)
            throw new InvalidDatesException();
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationLevel = educationLevel;
        this.gpa = gpa;
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
}

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
}

class InvalidDatesException extends Exception {
    public InvalidDatesException() {
        super();
    }
}

class User extends Consumer {
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

class Employee extends Consumer {
    String companyName;
    Double salary;

    public Employee(User user) {
        this.resume = user.resume;
        this.friends = user.friends;
        this.companyName = null;
        this.salary = null;
    }

    public Employee(String companyName, Double salary) {
        super();
        this.companyName = companyName;
        this.salary = salary;
    }
}

class Recruiter extends Employee {
    double rating;

    public Recruiter(String companyName, Double salary) {
        super(companyName, salary);
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
        currentCompany.manager.requests.add(new Request<Job, Consumer>(job, user, this, score));
        rating += 0.1;
        return (int) (rating * user.getTotalScore());
    }
}

class Request<K, V> implements Comparable<Request>{
    private K key;
    private V value1, value2;
    private Double score;

    public Request(K key, V value1, V value2, Double score) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.score = score;
    }

    public K getKey() {
        return key;
    }

    public V getValue1() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }

    public Double getScore() {
        return score;
    }

    public String toString() {
        return "Key: " + key + " ; Value1: " + value1 + " ; Value2: " + value2 + " ; Score: " + score;
    }

    @Override
    public int compareTo(Request o) {
        if (score < o.score)
            return -1;
        else if (score > o.score)
            return 1;
        return 0;
    }
}

class Manager extends Employee {
    ArrayList<Request<Job, Consumer>> requests;

    public Manager(User user) {
        super(user);
    }

    public void process(Job job) {
        ArrayList<Request<Job, Consumer>> jobRequests = new ArrayList<>();
        for (Request<Job, Consumer> request : requests) {
            if (request.getKey().equals(job)) {
                jobRequests.add(request);
            }
        }
        Collections.sort(jobRequests);
        int i = 0;
        while (job.noPositions > 0) {
            if (i >= jobRequests.size())
                break;
            //TODO verify if user can be enrolled into company
            i++;
        }
    }
}


class Job {
    String jobName, companyName;
    Boolean open;
    Constraint graduationYear, experienceYears, GPA;
    int noPositions;
    public void apply(User user) {
        if (open) {
            // TODO find best recruiter to evaluate user
        }
    }

    public boolean meetsRequirments(User user) {
        if (user.getGraduationYear() < graduationYear.lowerBound || user.getGraduationYear() > graduationYear.upperBound)
            return false;

        if (user.getTotalScore() < experienceYears.lowerBound || user.getTotalScore() > experienceYears.lowerBound)
            return false;

        if (user.meanGPA() < GPA.lowerBound || user.meanGPA() > GPA.upperBound)
            return false;

        return true;
    }
}

class Constraint {
    double lowerBound, upperBound;

    public Constraint(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
}

// TODO
class Company {
    String name;
    Manager manager;
    ArrayList<Department> departments;
    ArrayList<Recruiter> recruiters;

    public void add(Department department) {

    }

    public void add(Recruiter recruiter) {

    }

    public void add(Employee employee, Department department) {

    }

    public void remove(Employee employee) {

    }

    public void remove(Department department) {

    }

    public void remove(Recruiter recruiter) {

    }

    public void move(Department source, Department destination) {

    }

    public void move(Employee employee, Department newDepartment) {

    }

    public boolean contains(Department department) {

    }

    public boolean contains(Employee employee) {

    }

    public boolean contains(Recruiter recruiter) {

    }

    public Recruiter getRecruiter(User user) {

    }

    public ArrayList<Job> getJobs() {

    }
}

// TODO
abstract class Department {
    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs() {

    }

    public void add(Employee employee) {

    }

    public void remove(Employee employee) {

    }

    public void add(Job job) {

    }

    public ArrayList<Employee> getEmployees() {

    }
}

// TODO
class IT {

}

// TODO
class Management {

}

// TODO
class Marketing {

}

// TODO
class Finance {

}


