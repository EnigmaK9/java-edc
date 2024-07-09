package main.java.views;

import main.java.dao.EmployeeDAO;
import main.java.models.Employee;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;
import main.java.EmployeeManagementSystem;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userLabel, passwordLabel;

    public LoginFrame() {
        setTitle("Login - Employee Management System - Sistema de administración de empleados");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Color de fondo

        userLabel = new JLabel("Username:");
        userLabel.setForeground(new Color(46, 139, 87)); // Color de texto
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(46, 139, 87)); // Color de texto

        userField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(46, 139, 87)); // Color de fondo del botón
        loginButton.setForeground(Color.WHITE); // Color de texto del botón
        loginButton.setPreferredSize(new Dimension(100, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    // Credenciales correctas, mostrar ventana principal
                    EventQueue.invokeLater(() -> {
                        try {
                            EmployeeManagementSystem window = new EmployeeManagementSystem();
                            window.frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    dispose(); // Cerrar la ventana de login
                } else {
                    // Credenciales incorrectas, mostrar mensaje de error
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
