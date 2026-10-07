import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReportsWindow extends JFrame {
    public ReportsWindow(int userId) {
        setTitle("Reports");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Replace with dynamic report generation
        JTextArea reportArea = new JTextArea("Generated Report...");
        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(scrollPane, BorderLayout.CENTER);
    }
}
