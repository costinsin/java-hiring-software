import java.time.LocalDate;
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
        // TODO test & rework
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

        public Resume(ResumeBuilder builder) throws ResumeIncompleteException {
            this.informatrion = new Informatrion();
            this.informatrion.setFirstName(builder.firstName);
            this.informatrion.setLastName(builder.lastName);
            this.informatrion.setEmail(builder.email);
            this.informatrion.setPhoneNumber(builder.phone);
            this.informatrion.setGenre(builder.genre);
            this.informatrion.setBirthDate(builder.birthDate);
            this.informatrion.setLanguages(builder.languages);

            this.education = builder.educationSet;

            this.experience = builder.experienceSet;

            if (this.education.isEmpty() ||  this.informatrion.getFirstName() == null ||
                    this.informatrion.getLastName() == null || this.informatrion.getGenre() == null ||
                    this.informatrion.getBirthDate() == null)
                throw new ResumeIncompleteException();
        }

        public static class ResumeIncompleteException extends Exception {
            public ResumeIncompleteException() {
                super();
            }
        }

        public static class ResumeBuilder {
            String firstName, lastName, email, phone, genre;
            LocalDate birthDate;
            ArrayList<Informatrion.Language> languages = new ArrayList<>();
            TreeSet<Education> educationSet = new TreeSet<>();
            TreeSet<Experience> experienceSet = new TreeSet<>();

            public ResumeBuilder(String firstName, String lastName, String genre, LocalDate birthDate) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.genre = genre;
                this.birthDate = birthDate;
            }

            public ResumeBuilder email(String email) {
                this.email = email;
                return this;
            }

            public ResumeBuilder phone(String phone) {
                this.phone = phone;
                return this;
            }

            public ResumeBuilder language(Informatrion.Language language) {
                this.languages.add(language);
                return this;
            }

            public ResumeBuilder education(Education education) {
                this.educationSet.add(education);
                return this;
            }

            public ResumeBuilder education(Experience experience) {
                this.experienceSet.add(experience);
                return this;
            }

            public Resume build() {
                Resume resume = new Resume();
                resume.informatrion.setFirstName(this.firstName);
                resume.informatrion.setLastName(this.lastName);
                resume.informatrion.setEmail(this.email);
                resume.informatrion.setPhoneNumber(this.phone);
                resume.informatrion.setGenre(this.genre);
                resume.informatrion.setBirthDate(this.birthDate);
                resume.informatrion.setLanguages(this.languages);

                resume.education = this.educationSet;

                resume.experience = this.experienceSet;

                return resume;
            }
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
