import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class LoginPage extends Page {
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton loginButton;

    public LoginPage() {
       /* User user = new User();
        user.resume = new Consumer.Resume.ResumeBuilder("Costin", "Sin", "man", LocalDate.of(2000, 9, 23)).build();
        user.notifications.add(new Notification("Ai fost angajat de Bloomberg"));
        user.notifications.add(new Notification("Ai fost respins de Google"));
        Application.getInstance().add(user);

        Company company = new Company();
        User user2 = new User();
        user2.resume = new Consumer.Resume.ResumeBuilder("Robert", "Sin", "man", LocalDate.of(2000, 9, 23))
                .email("robertsin@gmail.com")
                .phone("+40745751362")
                .build();
        user2.notifications.add(new Notification("Ai fost angajat de Bloomberg"));
        user2.notifications.add(new Notification("Ai fost respins de Google"));
        company.manager = new Manager(user2);

        Job job = new Job();
        job.jobName = "Software Engineer Intern";
        job.noPositions = 5;

        User applicant = new User();
        applicant.resume = new Consumer.Resume.ResumeBuilder("Catalin", "Dumitru", "man", LocalDate.of(2000, 1, 17)).build();

        User recruiter = new User();
        recruiter.resume = new Consumer.Resume.ResumeBuilder("Andrei", "Mihailescu", "man", LocalDate.of(2000, 6, 21)).build();

        //Application.getInstance().add(applicant);


        company.manager.requests.add(new Request<Job, Consumer>(job, applicant, recruiter, 50.0));
        //Application.getInstance().add(company);*/

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Login errors
                if (usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "No username input");
                    return;
                }

                /*if (passwordField.getPassword().length < 3) {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Password is too short");
                    return;
                }*/

                Consumer foundUser = findUser(usernameField.getText());
                if (foundUser == null && !usernameField.getText().equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Username does not exist");
                    return;
                }

                // Page changing
                if (foundUser instanceof User)
                    changePage(new UserWelcomePage((User) foundUser).getPanel());
                else if (foundUser instanceof Manager)
                    changePage(new ManagerWelcomePage((Manager) foundUser).getPanel());
                else if (foundUser instanceof Employee)
                    changePage(new UserWelcomePage(new User()).getPanel());
                else if (usernameField.getText().equalsIgnoreCase("admin"))
                    changePage(new AdminPage().getPanel());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return loginPanel;
    }

    public static Consumer findUser(String username) {
        // Check application user
        for (User user : Application.getInstance().users) {
            String userUsername = user.resume.informatrion.getFirstName() + user.resume.informatrion.getLastName();
            if (username.equalsIgnoreCase(userUsername)) {
                return user;
            }
        }

        for(Company company : Application.getInstance().companies) {
            // Check company manager
            String managerUsername = company.manager.resume.informatrion.getFirstName() + company.manager.resume.informatrion.getLastName();
            if (username.equalsIgnoreCase(managerUsername)) {
                return company.manager;
            }

            // Check company employee
            for(Department department : company.departments) {
                for (Employee employee : department.employees) {
                    String employeeUsername = employee.resume.informatrion.getFirstName() + employee.resume.informatrion.getLastName();
                    if (username.equalsIgnoreCase(employeeUsername)) {
                        return employee;
                    }
                }
            }
        }
        return null;
    }
}
