import javax.swing.*;
import java.awt.*;

public class InstructorDashboard extends JFrame {
    private final int instructorId;

    public InstructorDashboard(int instructorId) {
        this.instructorId = instructorId;

        setTitle("Instructor Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton courseManagementButton = new JButton("Manage Courses");
        JButton reportsButton = new JButton("View Reports");
        JButton logoutButton = new JButton("Logout");

        panel.add(courseManagementButton);
        panel.add(reportsButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.CENTER);

        courseManagementButton.addActionListener(e -> new CourseManagementWindow(instructorId).setVisible(true));
        reportsButton.addActionListener(e -> new ReportsWindow(instructorId).setVisible(true));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginUI.setVisible(true);
        });
    }
}
