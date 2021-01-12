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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(companies, that.companies) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies, users);
    }
}


