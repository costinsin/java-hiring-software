import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Manager extends Employee {
    ArrayList<Request<Job, Consumer>> requests;

    public Manager(User user) {
        super(user);
        requests = new ArrayList<>();
    }

    public Manager(Test.MyConsumer consumer) throws InvalidDatesException {
        super(consumer);
        requests = new ArrayList<>();
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

            /*// approve
            if (Application.getInstance().users.contains((User) jobRequests.get(i).getValue1())) {
                // Remove user
                Application.getInstance().remove((User) jobRequests.get(i).getValue1());
                job.noPositions--;

                // Get company department
                Company company = Application.getInstance().getCompany(this.companyName);
                Department jobDepartment = null;
                for (Department department : company.departments) {
                    if (department.jobs.contains(jobRequests.get(i).getKey())) {
                        jobDepartment = department;
                        break;
                    }
                }

                // Convert user to employee and add it to jobDepartment
                assert jobDepartment != null;
                jobDepartment.employees.add(new Employee((User) jobRequests.get(i).getValue1(), this.companyName, 5000.0));

                // Remove observer from all companies
                for (Company comp : Application.getInstance().companies) {
                    comp.removeObserver((User) jobRequests.get(i).getValue1());
                }
            }*/
            approve(jobRequests.get(0));

            i++;
        }

        if (job.noPositions == 0) {
            job.open = false;
        }
    }

    public int approve(Request<Job, Consumer> request) {
        if (Application.getInstance().users.contains((User) request.getValue1()) && request.getKey().open) {
            // Remove user
            Application.getInstance().remove((User) request.getValue1());
            request.getKey().noPositions--;

            if (request.getKey().noPositions == 0) {
                request.getKey().open = false;
            }

            // Get company department
            Company company = Application.getInstance().getCompany(this.companyName);
            Department jobDepartment = null;
            for (Department department : company.departments) {
                if (department.jobs.contains(request.getKey())) {
                    jobDepartment = department;
                    break;
                }
            }

            // Convert user to employee and add it to jobDepartment
            if (jobDepartment == null)
                return 1;
            jobDepartment.employees.add(((User) request.getValue1()).convert(this.companyName, 5000.0));

            // Remove observer from all companies
            for (Company comp : Application.getInstance().companies) {
                comp.removeObserver((User) request.getValue1());
            }

            // Remove request after it was processed
            requests.remove(request);

            return 0;
        }
        return 1;
    }

    public void deny(Request<Job, Consumer> request) {
        requests.remove(request);
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
        Manager manager = (Manager) o;
        return Objects.equals(requests, manager.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), requests);
    }
}
