import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {
    private int adminId;

    public AdminDashboard(int adminId) {
        this.adminId = adminId;

        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JButton userManagementButton = new JButton("User Management");
        JButton courseManagementButton = new JButton("Manage Courses");
        JButton reportsButton = new JButton("View Reports");
        JButton updateProfileButton = new JButton("Update Profile");
        JButton logoutButton = new JButton("Logout");

        panel.add(userManagementButton);
        panel.add(courseManagementButton);
        panel.add(reportsButton);
        panel.add(updateProfileButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.CENTER);

        userManagementButton.addActionListener(e -> new UserManagementWindow().setVisible(true));
        courseManagementButton.addActionListener(e -> new CourseManagementWindow(adminId).setVisible(true));
        reportsButton.addActionListener(e -> new ReportsWindow(adminId).setVisible(true));
        updateProfileButton.addActionListener(e -> new UpdateProfileWindow(adminId).setVisible(true));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginUI().setVisible(true);
        });
    }
}
