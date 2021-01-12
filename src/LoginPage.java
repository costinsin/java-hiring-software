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
                // Page changing
                changePage(new WelcomePage(usernameField.getText()).getPanel());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return loginPanel;
    }
}
