import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends Page {
    private JPanel welcomePanel;
    private JButton signOutButton;
    private JLabel welcomeText;

    public WelcomePage(String username) {
        welcomeText.setText("Welcome, " + username + "!");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return welcomePanel;
    }
}
