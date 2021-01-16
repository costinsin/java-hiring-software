import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationPage extends Page {
    private JPanel notificationPanel;
    private JButton homeButton;
    private JButton searchProfileButton;
    private JButton notificationsButton;
    private JButton signOutButton;
    private JPanel notificationSpace;

    public NotificationPage(User user) {

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new UserWelcomePage(user).getPanel());
            }
        });
        searchProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new SearchPage(user).getPanel());
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });

        notificationSpace.setLayout(new BoxLayout(notificationSpace, BoxLayout.Y_AXIS));
        notificationSpace.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (Notification notification : user.notifications) {
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
            notificationSpace.add(new JNotification(notification).getPanel());
        }
    }

    @Override
    JPanel getPanel() {
        return notificationPanel;
    }
}
