import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Informatrion that = (Informatrion) o;
        return lastName.equals(that.lastName) &&
                firstName.equals(that.firstName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                sex.equals(that.sex) &&
                birthDate.equals(that.birthDate) &&
                Objects.equals(languages, that.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, phoneNumber, sex, birthDate, languages);
    }
}
