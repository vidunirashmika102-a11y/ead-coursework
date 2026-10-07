import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentDashboard extends JFrame {

    public StudentDashboard(int studentId) {

        setTitle("Student Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton enrollCoursesButton = new JButton("Enroll in Courses");
        JButton viewGradesButton = new JButton("View Grades");
        JButton updateProfileButton = new JButton("Update Profile");
        JButton logoutButton = new JButton("Logout");

        panel.add(enrollCoursesButton);
        panel.add(viewGradesButton);
        panel.add(updateProfileButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.CENTER);

        enrollCoursesButton.addActionListener(e -> new EnrollmentUI(studentId).setVisible(true));
        viewGradesButton.addActionListener(e -> new ViewGradesWindow(studentId).setVisible(true));
        updateProfileButton.addActionListener(e -> new UpdateProfileWindow(studentId).setVisible(true));
        logoutButton.addActionListener((ActionEvent e) -> {
            dispose();
            new LoginScreen().setVisible(true);
        });
    }
}
 
