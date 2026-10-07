import com.example.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupUI extends JFrame {
    public SignupUI() {
        setTitle("LMS Signup");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 200, 100, 30);
        String[] roles = {"Admin", "Instructor", "Student"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(150, 200, 200, 30);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(150, 250, 100, 30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 250, 100, 30);

        // Action for Signup Button
        signupButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Users (name, email, password, role) VALUES (?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, role);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Signup Successful");
                dispose(); // Close signup screen
                switch (role) {
                    case "Student":
                        new StudentDashboard(name.hashCode());
                        break;
                    case "Instructor":
                        new InstructorDashboard(name.hashCode());
                        break;
                    case "Admin":
                        new AdminDashboard(name.hashCode());
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unknown role: " + role);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Action for Back Button
        backButton.addActionListener(e -> {
            dispose();
            new LoginUI();
        });

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleComboBox);
        add(signupButton);
        add(backButton);

        setVisible(true);
    }
}