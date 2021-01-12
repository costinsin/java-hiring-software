import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Manager extends Employee {
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
