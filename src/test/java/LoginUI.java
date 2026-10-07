import com.example.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI extends JFrame {
    public LoginUI() {
        setTitle("LMS Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(260, 150, 100, 30);

        // Action for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE email = ? AND password = ?");
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String role = rs.getString("role");
                        JOptionPane.showMessageDialog(LoginUI.this, "Login Successful");
                        dispose(); // Close login screen
                        switch (role) {
                            case "Student":
                                new StudentDashboard(rs.getInt("id"));
                                break;
                            case "Instructor":
                                new InstructorDashboard(rs.getInt("id"));
                                break;
                            case "Admin":
                                new AdminDashboard(rs.getInt("id"));
                                break;
                            default:
                                JOptionPane.showMessageDialog(LoginUI.this, "Unknown role: " + role);
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(LoginUI.this, "Invalid Credentials");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginUI.this, "Error: " + ex.getMessage());
                }
            }
        });

        // Action for Signup Button
        signupButton.addActionListener((ActionEvent e) -> {
            dispose();
        });

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(signupButton);

        setVisible(true);
    }

    static class setVisible {

        public setVisible(boolean b) {
        }
    }
}