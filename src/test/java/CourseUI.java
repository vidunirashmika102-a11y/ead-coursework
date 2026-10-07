import com.example.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CourseUI extends JFrame {
    public CourseUI() {
        setTitle("Course Management");
        setSize(400, 300);
        setLayout(null);

        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);

        JButton addButton = new JButton("Add Course");
        addButton.setBounds(150, 100, 150, 30);

        addButton.addActionListener(e -> {
            String courseName = nameField.getText();
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Courses (course_name, instructor_id) VALUES (?, ?)");
                ps.setString(1, courseName);
                ps.setInt(2, 2); // Example instructor_id
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Course Added Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(nameLabel);
        add(nameField);
        add(addButton);

        setVisible(true);
    }
}
