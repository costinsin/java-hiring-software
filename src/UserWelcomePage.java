import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserWelcomePage extends Page {
    private JPanel welcomePanel;
    private JButton homeButton;
    private JButton searchProfileButton;
    private JButton signOutButton;
    private JButton notificationsButton;
    private JLabel welcomeText;

    public UserWelcomePage(User user) {
        welcomeText.setText("Welcome, " + user.resume.informatrion.getFirstName() + " " + user.resume.informatrion.getLastName() + "!");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
        searchProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new SearchPage(user).getPanel());
            }
        });
        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new NotificationPage(user).getPanel());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return welcomePanel;
    }
}
