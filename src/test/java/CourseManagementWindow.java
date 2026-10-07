import java.awt.BorderLayout;
import javax.swing.*;

public class CourseManagementWindow extends JFrame {
    public CourseManagementWindow(int userId) {
        setTitle("Course Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Course Name", "Instructor", "Materials"};
        Object[][] data = {}; // Fetch from DB

        JTable courseTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(courseTable);

        JButton addCourseButton = new JButton("Add Course");
        JButton updateCourseButton = new JButton("Update Course");
        JButton deleteCourseButton = new JButton("Delete Course");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCourseButton);
        buttonPanel.add(updateCourseButton);
        buttonPanel.add(deleteCourseButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addCourseButton.addActionListener(e -> new AddCourseWindow(userId).setVisible(true));
        // Implement update and delete logic
    }
}
