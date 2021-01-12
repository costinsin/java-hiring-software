import java.util.*;

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
        // TODO rework
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(resume, consumer.resume) &&
                Objects.equals(friends, consumer.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resume, friends);
    }
}
