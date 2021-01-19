import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobRequestPage extends Page {
    private JPanel requestPanel;
    private JButton homeButton;
    private JButton searchProfileButton;
    private JButton jobRequestsButton;
    private JButton signOutButton;
    private JPanel requestSpace;

    public JobRequestPage(Manager manager) {

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new ManagerWelcomePage(manager).getPanel());
            }
        });
        searchProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new SearchPage(manager).getPanel());
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });

        requestSpace.setLayout(new BoxLayout(requestSpace, BoxLayout.Y_AXIS));
        requestSpace.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Request<Job, Consumer> request : manager.requests) {
            requestSpace.add(new JRequest(request, manager).getPanel());
        }
    }

    @Override
    JPanel getPanel() {
        return requestPanel;
    }
}
