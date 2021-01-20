import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends Page {
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton loginButton;

    public LoginPage() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Login errors
                if (usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "No username input");
                    return;
                }

                if (passwordField.getPassword().length < 3) {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Password is too short");
                    return;
                }

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
                    changePage(new EmployeeWelcomePage((Employee) foundUser).getPanel());
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
