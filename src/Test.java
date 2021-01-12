import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        Frame frame = Frame.getInstance();
    }
}

class Frame extends JFrame {
    private static Frame instance;

    private Frame() {
        super("Title");
        this.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new LoginPage().getPanel());
        this.pack();
        this.setVisible(true);
    }

    public static Frame getInstance() {
        if (instance == null)
            instance = new Frame();
        return instance;
    }
}

abstract class Page {
    abstract JPanel getPanel();
    void changePage(JPanel nextPageJPanel) {
        Frame.getInstance().setContentPane(nextPageJPanel);
        Frame.getInstance().revalidate();
        Frame.getInstance().repaint();
    }
}
