import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Vector;

public class Test {
    public static void main(String[] args) throws IOException, InvalidDatesException {
        loadJSON(Application.getInstance());
        Frame frame = Frame.getInstance();
    }

    public static void loadJSON(Application application) throws IOException, InvalidDatesException {
        Gson gson = new Gson();

        Reader reader = new FileReader("./test/consumers.json");

        MyApplication jsonObject = gson.fromJson(reader, MyApplication.class);

        Application instance = Application.getInstance();

        for (MyConsumer consumer : jsonObject.users) {
            instance.add(new User(consumer));
        }

        String[] companies = {"Google", "Amazon"};

    }

    public static class MyApplication {
        Vector<MyConsumer> employees;
        Vector<MyConsumer> recruiters;
        Vector<MyConsumer> users;
        Vector<MyConsumer> managers;
    }

    public static class MyConsumer {
        String name;
        String email;
        String phone;
        String date_of_birth;
        String genre;
        Double salary;
        ArrayList<String> languages;
        ArrayList<String> languages_level;
        ArrayList<String> interested_companies;
        ArrayList<MyEducation> education;
        ArrayList<MyExperience> experience;
    }

    public static class MyEducation {
        String level;
        String name;
        String start_date;
        String end_date;
        Double grade;
    }

    public static class MyExperience {
        String company;
        String position;
        String start_date;
        String end_date;
    }
}

class Frame extends JFrame {
    private static Frame instance;

    private Frame() {
        super("Hiring Software");
        this.setPreferredSize(new Dimension(600, 600));
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
