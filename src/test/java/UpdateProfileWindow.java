import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class UpdateProfileWindow extends JFrame {

    public UpdateProfileWindow(int userId) {
        setTitle("Update Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton updateButton = new JButton("Update Profile");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(updateButton);

        // This will now correctly reference java.awt.BorderLayout.CENTER
        add(panel, BorderLayout.CENTER);

        updateButton.addActionListener(e -> {
            // Implement DB update logic here
            JOptionPane.showMessageDialog(this, "Profile Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}