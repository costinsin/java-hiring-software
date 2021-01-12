import java.util.Objects;

public class Job {
    String jobName, companyName;
    Boolean open;
    Constraint graduationYear, experienceYears, GPA;
    int noPositions;

    public void apply(User user) {
        if (open && meetsRequirments(user)) {
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
