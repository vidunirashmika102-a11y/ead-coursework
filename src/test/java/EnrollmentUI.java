import com.example.DBConnection;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EnrollmentUI extends JFrame {
    public EnrollmentUI(int studentId) {
        setTitle("Enrollment Management");
        setSize(400, 300);
        setLayout(null);

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 50, 100, 30);
        JTextField studentIdField = new JTextField();
        studentIdField.setBounds(150, 50, 200, 30);

        JLabel courseIdLabel = new JLabel("Course ID:");
        courseIdLabel.setBounds(50, 100, 100, 30);
        JTextField courseIdField = new JTextField();
        courseIdField.setBounds(150, 100, 200, 30);

        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.setBounds(150, 150, 150, 30);

        enrollButton.addActionListener((ActionEvent e) -> {
            int studentId1 = Integer.parseInt(studentIdField.getText());
            int courseId = Integer.parseInt(courseIdField.getText());
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Enrollments (student_id, course_id) VALUES (?, ?)");
                ps.setInt(1, studentId1);
                ps.setInt(2, courseId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(EnrollmentUI.this, "Student Enrolled Successfully");
            }catch (Exception ex) {
            }
        });

        add(studentIdLabel);
        add(studentIdField);
        add(courseIdLabel);
        add(courseIdField);
        add(enrollButton);

        setVisible(true);
    }
}
