package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton, languageButton, registerButton, showPasswordButton, accessibilityButton, forgotPasswordButton;
    private JLabel userLabel, passwordLabel;
    private boolean isEnglish = true;
    private boolean isAccessible = false;

    public LoginFrame() {
        setTitle("Member Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar en pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        GradientPanel panel = new GradientPanel(new GridBagLayout());
        panel.setGradientColors(new Color(122, 71, 222), new Color(2, 222, 255)); // Colores del degradado

        JLabel logoLabel = new JLabel("L"); // Logo como letra gigante
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setFont(new Font("Serif", Font.BOLD, 100));
        logoLabel.setForeground(Color.WHITE);

        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE); // Color de texto blanco
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 24));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE); // Color de texto blanco
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 24));

        userField = new JTextField(15);
        userField.setFont(new Font("Roboto", Font.PLAIN, 24));
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 24));
        showPasswordButton = createStyledButton("Show");

        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getEchoChar() != '\u0000') {
                    passwordField.setEchoChar('\u0000');
                    showPasswordButton.setText("Hide");
                } else {
                    passwordField.setEchoChar('*');
                    showPasswordButton.setText("Show");
                }
            }
        });

        loginButton = createStyledButton("Login");
        registerButton = createStyledButton("New user");
        languageButton = createStyledButton("Español");
        accessibilityButton = createStyledButton("Accessibility");
        forgotPasswordButton = createStyledButton("Forgot Password");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 2;
        panel.add(showPasswordButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false); // Transparente para que se vea el degradado
        bottomPanel.add(languageButton, BorderLayout.WEST); // Botón de idioma a la izquierda
        bottomPanel.add(accessibilityButton, BorderLayout.EAST); // Botón de accesibilidad a la derecha
        bottomPanel.add(forgotPasswordButton, BorderLayout.CENTER); // Botón de "Forgot Password" en el centro
        add(bottomPanel, BorderLayout.SOUTH);

        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    // Credenciales correctas, mostrar ventana principal
                    EventQueue.invokeLater(() -> {
                        try {
                            EmployeeManagementFrame window = new EmployeeManagementFrame();
                            window.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    dispose(); // Cerrar la ventana de login
                } else {
                    // Credenciales incorrectas, mostrar mensaje de error
                    showMessageDialog(LoginFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        loginButton.addActionListener(loginAction);

        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchLanguage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerNewUser();
            }
        });

        accessibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAccessibility();
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para el botón de "Forgot Password"
                JOptionPane.showMessageDialog(LoginFrame.this, "Forgot Password functionality not implemented yet.");
            }
        });

        // Configurar para que la tecla Esc salga del programa
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                return false;
            }
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 199, 255)); // Color de fondo del botón
        button.setForeground(Color.WHITE); // Color de texto del botón
        button.setPreferredSize(new Dimension(150, 30));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Roboto", Font.BOLD, 24)); // Cambia el tamaño de la fuente aquí
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 199, 255));
            }
        });
        return button;
    }

    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String dbUser = "root";
        String dbPassword = ""; // Reemplaza 'root_password' con la contraseña de tu usuario root

        String sql = "select * from users where username = ? and password = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usuario autenticado
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    private void switchLanguage() {
        if (isEnglish) {
            userLabel.setText("Usuario:");
            passwordLabel.setText("Contraseña:");
            loginButton.setText("Iniciar sesión");
            languageButton.setText("English");
            registerButton.setText("Nuevo usuario");
            showPasswordButton.setText("Mostrar");
            isEnglish = false;
        } else {
            userLabel.setText("Username:");
            passwordLabel.setText("Password:");
            loginButton.setText("Login");
            languageButton.setText("Español");
            registerButton.setText("New User");
            showPasswordButton.setText("Show");
            isEnglish = true;
        }
    }

    private void registerNewUser() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        JPasswordField adminPasswordField = new JPasswordField(20);
        JButton showAdminPasswordButton = createStyledButton(isEnglish ? "Show" : "Mostrar");

        showAdminPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminPasswordField.getEchoChar() != '\u0000') {
                    adminPasswordField.setEchoChar('\u0000');
                    showAdminPasswordButton.setText(isEnglish ? "Hide" : "Ocultar");
                } else {
                    adminPasswordField.setEchoChar('*');
                    showAdminPasswordButton.setText(isEnglish ? "Show" : "Mostrar");
                }
            }
        });

        adminPanel.add(adminPasswordField, BorderLayout.CENTER);
        adminPanel.add(showAdminPasswordButton, BorderLayout.EAST);

        int adminOption = JOptionPane.showConfirmDialog(this, adminPanel, isEnglish ? "Enter admin password:" : "Ingrese contraseña de administrador:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (adminOption == JOptionPane.OK_OPTION) {
            String adminPassword = new String(adminPasswordField.getPassword());
            if (adminPassword.equals("lalilulelo")) {
                JPanel panel = new JPanel(new GridBagLayout());
                panel.setBackground(new Color(240, 240, 240)); // Fondo estándar sin degradado
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                JLabel fullNameLabel = new JLabel(isEnglish ? "Full Name:" : "Nombre Completo:");
                fullNameLabel.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                JTextField fullNameField = new JTextField(15);
                fullNameField.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(fullNameLabel, gbc);
                gbc.gridx = 1;
                panel.add(fullNameField, gbc);

                JLabel emailLabel = new JLabel(isEnglish ? "Email:" : "Correo:");
                emailLabel.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                JTextField emailField = new JTextField(15);
                emailField.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(emailLabel, gbc);
                gbc.gridx = 1;
                panel.add(emailField, gbc);

                JLabel usernameLabel = new JLabel(isEnglish ? "Username:" : "Usuario:");
                usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                JTextField usernameField = new JTextField(15);
                usernameField.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(usernameLabel, gbc);
                gbc.gridx = 1;
                panel.add(usernameField, gbc);

                JLabel passwordLabel = new JLabel(isEnglish ? "Password:" : "Contraseña:");
                passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                JPasswordField passwordField = new JPasswordField(15);
                passwordField.setFont(new Font("Roboto", Font.PLAIN, 24)); // Ajustar tamaño de letra
                JButton showPasswordButton = createStyledButton(isEnglish ? "Show" : "Mostrar");

                showPasswordButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (passwordField.getEchoChar() != '\u0000') {
                            passwordField.setEchoChar('\u0000');
                            showPasswordButton.setText(isEnglish ? "Hide" : "Ocultar");
                        } else {
                            passwordField.setEchoChar('*');
                            showPasswordButton.setText(isEnglish ? "Show" : "Mostrar");
                        }
                    }
                });

                gbc.gridx = 0;
                gbc.gridy = 3;
                panel.add(passwordLabel, gbc);
                gbc.gridx = 1;
                panel.add(passwordField, gbc);
                gbc.gridx = 2;
                panel.add(showPasswordButton, gbc);

                int option = JOptionPane.showConfirmDialog(this, panel, isEnglish ? "Register New User" : "Registrar Nuevo Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String fullName = fullNameField.getText();
                    String email = emailField.getText();
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (isPasswordValid(password)) {
                        createUser(fullName, email, username, password);
                    } else {
                        String errorMessage = isEnglish ? "Password must be at least 8 characters long and contain an uppercase letter, a lowercase letter, and a number." : "La contraseña debe tener al menos 8 caracteres y contener una letra mayúscula, una letra minúscula y un número.";
                        showMessageDialog(this, errorMessage, isEnglish ? "Invalid Password" : "Contraseña Inválida", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                String errorMessage = isEnglish ? "Invalid admin password." : "Contraseña de administrador inválida.";
                showMessageDialog(this, errorMessage, isEnglish ? "Authentication Error" : "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }
        return true;
    }

    private void createUser(String fullName, String email, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String dbUser = "root";
        String dbPassword = ""; // Reemplaza 'root_password' con la contraseña de tu usuario root

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword)) {
            String sql = "insert into users (full_name, email, username, password) values (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, fullName);
                stmt.setString(2, email);
                stmt.setString(3, username);
                stmt.setString(4, password);
                stmt.executeUpdate();

                String successMessage = isEnglish ? "User created successfully!" : "¡Usuario creado con éxito!";
                showMessageDialog(this, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            String errorMessage = isEnglish ? "Error creating user: " : "Error al crear el usuario: ";
            showMessageDialog(this, errorMessage + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessageDialog(Component parentComponent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }

    private void toggleAccessibility() {
        if (isAccessible) {
            userLabel.setFont(userLabel.getFont().deriveFont(24f));
            passwordLabel.setFont(passwordLabel.getFont().deriveFont(24f));
            userField.setFont(userField.getFont().deriveFont(24f));
            passwordField.setFont(passwordField.getFont().deriveFont(24f));
            loginButton.setFont(loginButton.getFont().deriveFont(24f));
            registerButton.setFont(registerButton.getFont().deriveFont(24f));
            languageButton.setFont(languageButton.getFont().deriveFont(24f));
            showPasswordButton.setFont(showPasswordButton.getFont().deriveFont(24f));
            accessibilityButton.setFont(accessibilityButton.getFont().deriveFont(24f));
            forgotPasswordButton.setFont(forgotPasswordButton.getFont().deriveFont(24f));
            setSize(800, 600); // Restaurar tamaño original
        } else {
            userLabel.setFont(userLabel.getFont().deriveFont(24f));
            passwordLabel.setFont(passwordLabel.getFont().deriveFont(24f));
            userField.setFont(userField.getFont().deriveFont(24f));
            passwordField.setFont(passwordField.getFont().deriveFont(24f));
            loginButton.setFont(loginButton.getFont().deriveFont(24f));
            registerButton.setFont(registerButton.getFont().deriveFont(24f));
            languageButton.setFont(languageButton.getFont().deriveFont(24f));
            showPasswordButton.setFont(showPasswordButton.getFont().deriveFont(24f));
            accessibilityButton.setFont(accessibilityButton.getFont().deriveFont(24f));
            forgotPasswordButton.setFont(forgotPasswordButton.getFont().deriveFont(24f));
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar ventana
        }
        isAccessible = !isAccessible;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class GradientPanel extends JPanel {
    private Color color1;
    private Color color2;

    public GradientPanel(LayoutManager layout) {
        super(layout);
    }

    public void setGradientColors(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
