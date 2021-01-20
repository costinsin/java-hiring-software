import java.time.LocalDate;
import java.util.Objects;

public class Job {
    String jobName, companyName;
    Boolean open;
    Constraint graduationYear, experienceYears, GPA;
    int noPositions;

    public void apply(User user) {
        if (open && meetsRequirments(user)) {
            Recruiter recruiter = Application.getInstance().getCompany(companyName).getRecruiter(user);
            recruiter.evaluate(this, user);
        } else
            System.out.println(user.toString() + " does not qualify to " + companyName + " " + jobName);
    }

    public boolean meetsRequirments(User user) {
        Integer gradYear = user.getGraduationYear();
        if  (gradYear == null)
            gradYear = LocalDate.now().getYear();
        if (!graduationYear.respectsConstraint(gradYear.doubleValue())) {
            System.out.print("Graduation year fail: ");
            return false;
        }
        Double expYears = (user.getTotalScore() - user.meanGPA()) / 1.5;
        if (!experienceYears.respectsConstraint(expYears)) {
            System.out.print("Experience years fail: ");
            return false;
        }
        if (!GPA.respectsConstraint(user.meanGPA())) {
            System.out.print("GPA fail: ");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return jobName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return noPositions == job.noPositions &&
                Objects.equals(jobName, job.jobName) &&
                Objects.equals(companyName, job.companyName) &&
                Objects.equals(open, job.open) &&
                Objects.equals(graduationYear, job.graduationYear) &&
                Objects.equals(experienceYears, job.experienceYears) &&
                Objects.equals(GPA, job.GPA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, companyName, open, graduationYear, experienceYears, GPA, noPositions);
    }
}
