import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class EmployeeWelcomePage extends Page {
    private JPanel welcomePanel;
    private JButton logOutButton;
    private JButton workButton;
    private JLabel welcomeMessage;
    private JButton takeABreakButton;

    public EmployeeWelcomePage(Employee employee) {
        welcomeMessage.setText("Welcome to work, " + employee.toString() + "!");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
        workButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(URI.create("https://youtu.be/HL1UzIK-flA?t=18"));
                    } catch (Exception ignored) {

                    }
                }
            }
        });
        takeABreakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(URI.create("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                    } catch (Exception ignored) {

                    }
                }
            }
        });
    }

    @Override
    JPanel getPanel() {
        return welcomePanel;
    }
}
