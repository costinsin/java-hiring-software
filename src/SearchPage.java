import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

public class SearchPage extends Page {
    private JButton homeButton;
    private JButton searchProfileButton;
    private JButton signOutButton;
    private JButton customButton;
    private JPanel searchPanel;
    private JTextField usernameField;
    private JButton searchButton;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField genreField;
    private JTextField birthField;
    private JTextArea languageField;
    private JTextArea educationField;
    private JTextArea experienceField;

    public SearchPage(User user) {
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new UserWelcomePage(user).getPanel());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consumer foundUser = LoginPage.findUser(usernameField.getText());
                if (foundUser != null) {
                    firstnameField.setText(foundUser.resume.informatrion.getFirstName());
                    lastnameField.setText(foundUser.resume.informatrion.getLastName());
                    emailField.setText(foundUser.resume.informatrion.getEmail());
                    phoneField.setText(foundUser.resume.informatrion.getPhoneNumber());
                    genreField.setText(foundUser.resume.informatrion.getGenre());
                    birthField.setText(foundUser.resume.informatrion.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    languageField.setText(formatArrays(foundUser.resume.informatrion.getLanguages()));
                    educationField.setText(formatArrays(foundUser.resume.education));
                    experienceField.setText(formatArrays(foundUser.resume.experience));
                } else
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Username does not exist");
            }
        });
        customButton.setText("Notifications");
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new NotificationPage(user).getPanel());
            }
        });
    }

    public SearchPage(Manager manager) {
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new ManagerWelcomePage(manager).getPanel());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consumer foundUser = LoginPage.findUser(usernameField.getText());
                if (foundUser != null) {
                    firstnameField.setText(foundUser.resume.informatrion.getFirstName());
                    lastnameField.setText(foundUser.resume.informatrion.getLastName());
                    emailField.setText(foundUser.resume.informatrion.getEmail());
                    phoneField.setText(foundUser.resume.informatrion.getPhoneNumber());
                    genreField.setText(foundUser.resume.informatrion.getGenre());
                    birthField.setText(foundUser.resume.informatrion.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    languageField.setText(formatArrays(foundUser.resume.informatrion.getLanguages()));
                    educationField.setText(formatArrays(foundUser.resume.education));
                    experienceField.setText(formatArrays(foundUser.resume.experience));
                } else
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Username does not exist");
            }
        });
        customButton.setText("Job Requests");
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new JobRequestPage(manager).getPanel());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return searchPanel;
    }

    public <T> String formatArrays(TreeSet<T> list) {
        StringBuilder result = new StringBuilder();
        for (Object line : list)
            result.append(line.toString()).append("\n");
        return result.toString();
    }

    public <T> String formatArrays(ArrayList<T> list) {
        StringBuilder result = new StringBuilder();
        for (Object line : list)
            result.append(line.toString()).append("\n");
        return result.toString();
    }
}
