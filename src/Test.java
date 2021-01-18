import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Vector;

public class Test {
    public static void main(String[] args) throws IOException, InvalidDatesException {
        loadJSON(Application.getInstance());
        Frame frame = Frame.getInstance();
    }

    public static void loadJSON(Application application) throws IOException, InvalidDatesException {
        Gson gson = new Gson();

        MyApplication jsonObject = gson.fromJson(
                new FileReader("./test/consumers.json"),
                MyApplication.class
        );

        ArrayList<String> companyList = gson.fromJson(
                new FileReader("./test/companies.json"),
                ArrayList.class
        );

        Application instance = Application.getInstance();

        for (MyConsumer consumer : jsonObject.users) {
            instance.add(new User(consumer));
        }

        for (String companyName : companyList) {
            Company company = new Company(companyName);

            // Search company's manager
            for (MyConsumer manager : jsonObject.managers) {
                if (manager.findCurrentExperience().company.equalsIgnoreCase(companyName)) {
                    company.manager = new Manager(manager);
                    break;
                }
            }

            // Search company's employees
            for (MyConsumer employee : jsonObject.employees) {
                if (employee.findCurrentExperience().company.equalsIgnoreCase(companyName)) {
                    for (Department department : company.departments) {
                        if (department.getClass().getName().equalsIgnoreCase(employee.findCurrentExperience().department)) {
                            department.add(new Employee(employee));
                            break;
                        }
                    }
                }
            }

            // Search company's recruiters
            for (MyConsumer recruiter : jsonObject.recruiters) {
                if (recruiter.findCurrentExperience().company.equalsIgnoreCase(companyName)) {
                    for (Department department : company.departments) {
                        if (department.getClass().getName().equalsIgnoreCase("IT")) {
                            department.add(new Recruiter(recruiter));
                            break;
                        }
                    }
                }
            }

            instance.add(company);
        }
    }

    public static class MyApplication {
        Vector<MyConsumer> employees;
        Vector<MyConsumer> recruiters;
        Vector<MyConsumer> users;
        Vector<MyConsumer> managers;
    }

    public static class MyConsumer {
        String name;
        String email;
        String phone;
        String date_of_birth;
        String genre;
        Double salary;
        ArrayList<String> languages;
        ArrayList<String> languages_level;
        ArrayList<String> interested_companies;
        ArrayList<MyEducation> education;
        ArrayList<MyExperience> experience;

        public MyExperience findCurrentExperience() {
            for (MyExperience exp : experience) {
                if (exp.end_date == null)
                    return exp;
            }
            return null;
        }
    }

    public static class MyEducation {
        String level;
        String name;
        String start_date;
        String end_date;
        Double grade;
    }

    public static class MyExperience {
        String company;
        String position;
        String department;
        String start_date;
        String end_date;
    }
}

class Frame extends JFrame {
    private static Frame instance;

    private Frame() {
        super("Hiring Software");
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new LoginPage().getPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static Frame getInstance() {
        if (instance == null)
            instance = new Frame();
        return instance;
    }
}

abstract class Page {
    abstract JPanel getPanel();
    void changePage(JPanel nextPageJPanel) {
        Frame.getInstance().setContentPane(nextPageJPanel);
        Frame.getInstance().revalidate();
        Frame.getInstance().repaint();
    }
}
