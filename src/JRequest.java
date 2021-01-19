import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JRequest extends Page {
    private JPanel requestPanel;
    private JButton approveButton;
    private JButton denyButton;
    private JLabel jobName;
    private JLabel applicantName;
    private JLabel recruiterName;
    private JLabel score;
    private JLabel openPositions;

    public JRequest(Request<Job, Consumer> jobRequest, Manager manager) {
        jobName.setText(jobRequest.getKey().jobName);
        openPositions.setText(((Integer) jobRequest.getKey().noPositions) + " positions open");
        applicantName.setText(jobRequest.getValue1().resume.informatrion.getFirstName() + " "
                + jobRequest.getValue1().resume.informatrion.getLastName());
        recruiterName.setText(jobRequest.getValue2().resume.informatrion.getFirstName() + " "
                + jobRequest.getValue2().resume.informatrion.getLastName());
        score.setText("Score: " + String.format("%.2f", jobRequest.getScore()));
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.approve(jobRequest) == 0) {
                    requestPanel.setVisible(false);
                    changePage(new JobRequestPage(manager).getPanel());
                }
                else {
                    JOptionPane.showMessageDialog(Frame.getInstance(), "Can't approve this request!");
                }
            }
        });
        denyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.deny(jobRequest);
                requestPanel.setVisible(false);
                changePage(new JobRequestPage(manager).getPanel());
            }
        });
    }

    public JPanel getPanel() {
        return requestPanel;
    }
}
