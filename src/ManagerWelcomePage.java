import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerWelcomePage extends Page {
    private JPanel welcomePanel;
    private JButton homeButton;
    private JButton searchProfileButton;
    private JButton jobRequestsButton;
    private JButton signOutButton;
    private JLabel welcomeText;
    private JLabel requestNotifier;

    public ManagerWelcomePage(Manager manager) {
        welcomeText.setText("Welcome, manager " + manager.resume.informatrion.getFirstName() + " " + manager.resume.informatrion.getLastName() + "!");
        requestNotifier.setText("You have " + manager.requests.size() + " job requests wating for your approval");
        searchProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new SearchPage(manager).getPanel());
            }
        });
        jobRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new JobRequestPage(manager).getPanel());
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
    }

    @Override
    JPanel getPanel() {
        return welcomePanel;
    }
}
