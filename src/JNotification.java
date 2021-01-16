import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class JNotification {
    private JLabel notificationMessage;
    private JPanel notificationPanel;
    private JLabel time;

    public JNotification(Notification notification) {
        notificationMessage.setIcon(new ImageIcon(new ImageIcon("./lib/notification.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        notificationMessage.setText(notification.text);
        time.setText(notification.dateTime.format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-yyyy")));
    }

    public JPanel getPanel() {
        return notificationPanel;
    }
}
