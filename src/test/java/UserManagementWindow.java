import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManagementWindow extends JFrame {
    private final JTable userTable;
    private final DefaultTableModel tableModel;

    public UserManagementWindow() {
        setTitle("User Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Name", "Email", "Role"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        JButton addButton = new JButton("Add User");
        JButton updateButton = new JButton("Update User");
        JButton deleteButton = new JButton("Delete User");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load Users
        loadUsers();

        // Button Actions
        addButton.addActionListener(e -> new AddUserWindow().setVisible(true));
        updateButton.addActionListener(e -> updateUser());
        deleteButton.addActionListener(e -> deleteUser());
    }

    private void loadUsers() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT user_id, name, email, role FROM Users";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading users.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        // Placeholder for update logic
        JOptionPane.showMessageDialog(this, "Update user functionality is not implemented yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
