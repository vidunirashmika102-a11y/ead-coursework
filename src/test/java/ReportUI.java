import com.example.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReportUI extends JFrame {
    public ReportUI() {
        setTitle("Reports");
        setSize(600, 400);
        setLayout(null);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT u.name AS student_name, c.course_name FROM Enrollments e "
                    + "JOIN Users u ON e.student_id = u.user_id "
                    + "JOIN Courses c ON e.course_id = c.course_id";
            ResultSet rs = con.createStatement().executeQuery(query);

            DefaultTableModel model = new DefaultTableModel(new String[]{"Student", "Course"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("student_name"), rs.getString("course_name")});
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(scrollPane);
        setVisible(true);
    }
}

