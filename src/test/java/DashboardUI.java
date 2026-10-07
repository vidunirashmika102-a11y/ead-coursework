import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DashboardUI extends JFrame {
    public DashboardUI(String userName, String userRole) {
        setTitle("Dashboard - Welcome, " + userName + " (" + userRole + ")");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!", JLabel.CENTER);
        JLabel roleLabel = new JLabel("Your Role: " + userRole, JLabel.CENTER);

        JButton manageCoursesButton = new JButton("Manage Courses");
        JButton generateReportsButton = new JButton("Generate Reports");

        add(welcomeLabel);
        add(roleLabel);
        add(manageCoursesButton);
        add(generateReportsButton);

        setVisible(true);
    }
}
