import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// Clase Employee
class Employee {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private String gender;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

// Clase DatabaseUtil para manejar la conexión a la base de datos
class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to register MySQL driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// Clase EmployeeDAO para manejar las operaciones CRUD
class EmployeeDAO {
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, address, phone, email, birthdate, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getAddress());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getEmail());
            pstmt.setDate(5, Date.valueOf(employee.getBirthdate()));
            pstmt.setString(6, employee.getGender());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name=?, address=?, phone=?, email=?, birthdate=?, gender=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getAddress());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getEmail());
            pstmt.setDate(5, Date.valueOf(employee.getBirthdate()));
            pstmt.setString(6, employee.getGender());
            pstmt.setInt(7, employee.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Employee selectEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setPhone(rs.getString("phone"));
                employee.setEmail(rs.getString("email"));
                employee.setBirthdate(rs.getDate("birthdate").toLocalDate());
                employee.setGender(rs.getString("gender"));
                return employee;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Employee> selectAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setPhone(rs.getString("phone"));
                employee.setEmail(rs.getString("email"));
                employee.setBirthdate(rs.getDate("birthdate").toLocalDate());
                employee.setGender(rs.getString("gender"));
                employees.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }
}

// Clase LoginFrame para manejar la pantalla de inicio de sesión
class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userLabel, passwordLabel;

    public LoginFrame() {
        setTitle("Login - Employee Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        userField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

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
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}

// Clase Principal EmployeeManagementSystem
public class EmployeeManagementSystem {
    JFrame frame;
    private JTextField idField, nameField, addressField, phoneField, emailField, birthdateField;
    private JComboBox<String> genderComboBox;
    private JButton insertButton, updateButton, deleteButton, selectButton, searchButton, languageButton;
    private JLabel idLabel, nameLabel, addressLabel, phoneLabel, emailLabel, birthdateLabel, genderLabel;
    private JTextArea resultArea;
    private EmployeeDAO employeeDAO;

    public EmployeeManagementSystem() {
        employeeDAO = new EmployeeDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Employee Management System - Carlos Padilla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtener las dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Establecer el tamaño inicial de la ventana a un cuarto de la pantalla
        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Forzar la ventana a ocupar un cuarto de la pantalla
        frame.setBounds(0, 0, screenWidth / 2, screenHeight / 2);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(230, 240, 250));

        // Configurar etiquetas y campos de texto
        Font font = new Font("Roboto", Font.PLAIN, 19); // Reducir el tamaño de fuente
        Font labelFont = new Font("Roboto", Font.BOLD, 22); // Reducir el tamaño de fuente

        idLabel = new JLabel("ID:");
        idLabel.setBounds(24, 24, 180, 42); // Reducir el tamaño
        idLabel.setFont(labelFont);
        frame.getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(240, 24, 1320, 42); // Reducir el tamaño
        idField.setFont(font);
        frame.getContentPane().add(idField);
        addInfoIcon(idField, "Enter your numeric ID");

        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(24, 84, 180, 42); // Reducir el tamaño
        nameLabel.setFont(labelFont);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(240, 84, 1320, 42); // Reducir el tamaño
        nameField.setFont(font);
        frame.getContentPane().add(nameField);
        addInfoIcon(nameField, "Enter your full name");

        addressLabel = new JLabel("Full Address:");
        addressLabel.setBounds(24, 144, 180, 42); // Reducir el tamaño
        addressLabel.setFont(labelFont);
        frame.getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(240, 144, 1320, 42); // Reducir el tamaño
        addressField.setFont(font);
        frame.getContentPane().add(addressField);
        addInfoIcon(addressField, "Enter your full address");

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(24, 204, 300, 42); // Reducir el tamaño
        phoneLabel.setFont(labelFont);
        frame.getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(324, 204, 1236, 42); // Reducir el tamaño
        phoneField.setFont(font);
        frame.getContentPane().add(phoneField);
        addInfoIcon(phoneField, "Enter phone number (example: 5255554321)");

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(24, 264, 300, 42); // Reducir el tamaño
        emailLabel.setFont(labelFont);
        frame.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(324, 264, 1236, 42); // Reducir el tamaño
        emailField.setFont(font);
        frame.getContentPane().add(emailField);
        addInfoIcon(emailField, "Enter email address (user@email.com)");

        birthdateLabel = new JLabel("Birthdate:");
        birthdateLabel.setBounds(24, 324, 300, 42); // Reducir el tamaño
        birthdateLabel.setFont(labelFont);
        frame.getContentPane().add(birthdateLabel);

        birthdateField = new JTextField();
        birthdateField.setBounds(324, 324, 1236, 42); // Reducir el tamaño
        birthdateField.setFont(font);
        frame.getContentPane().add(birthdateField);
        addInfoIcon(birthdateField, "Enter birthdate in format ddMMyyyy");

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(24, 384, 300, 42); // Reducir el tamaño
        genderLabel.setFont(labelFont);
        frame.getContentPane().add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setBounds(324, 384, 1236, 42); // Reducir el tamaño
        genderComboBox.setFont(font);
        frame.getContentPane().add(genderComboBox);
        addInfoIcon(genderComboBox, "Select gender");

        // Configurar botones
        insertButton = new JButton("Insert");
        insertButton.setBounds(60, 456, 144, 48); // Reducir el tamaño
        insertButton.setBackground(new Color(144, 238, 144));
        insertButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(insertButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(240, 456, 144, 48); // Reducir el tamaño
        updateButton.setBackground(new Color(135, 206, 235));
        updateButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(420, 456, 144, 48); // Reducir el tamaño
        deleteButton.setBackground(new Color(255, 99, 71));
        deleteButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(deleteButton);

        selectButton = new JButton("Select");
        selectButton.setBounds(600, 456, 144, 48); // Reducir el tamaño
        selectButton.setBackground(new Color(255, 228, 181));
        selectButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(selectButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(780, 456, 144, 48); // Reducir el tamaño
        searchButton.setBackground(new Color(173, 216, 230));
        searchButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(searchButton);

        languageButton = new JButton("Español");
        languageButton.setBounds(960, 456, 144, 48); // Reducir el tamaño
        languageButton.setFont(new Font("Roboto", Font.BOLD, 19));
        frame.getContentPane().add(languageButton);

        // Configurar área de resultados
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 17)); // Reducir el tamaño de fuente
        resultArea.setBackground(new Color(255, 255, 204));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(24, 528, 1776, 120); // Reducir el tamaño
        frame.getContentPane().add(scrollPane);

        // Listeners para los botones
        insertButton.addActionListener(e -> {
            try {
                if (validateFields()) {
                    insertEmployee();
                    resultArea.setText("Employee Inserted!");
                    clearFields();
                } else {
                    resultArea.setText("Error: Invalid input data.");
                }
            } catch (DateTimeParseException ex) {
                resultArea.setText("Error: Invalid date format. Use ddMMyyyy.");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                if (validateFields()) {
                    updateEmployee();
                    resultArea.setText("Employee Updated!");
                    clearFields();
                } else {
                    resultArea.setText("Error: Invalid input data.");
                }
            } catch (DateTimeParseException ex) {
                resultArea.setText("Error: Invalid date format. Use ddMMyyyy.");
            }
        });

        deleteButton.addActionListener(e -> {
            deleteEmployee();
            resultArea.setText("Employee Deleted!");
            clearFields();
        });

        selectButton.addActionListener(e -> {
            List<Employee> employees = selectEmployees();
            StringBuilder sb = new StringBuilder();
            for (Employee emp : employees) {
                sb.append(emp.getId()).append(", ").append(emp.getName()).append(", ").append(emp.getAddress())
                        .append(", ").append(emp.getPhone()).append(", ").append(emp.getEmail()).append(", ")
                        .append(emp.getBirthdate()).append(", ").append(emp.getGender()).append("\n");
            }
            resultArea.setText(sb.toString());
        });

        searchButton.addActionListener(e -> {
            try {
                if (!idField.getText().isEmpty() && isNumeric(idField.getText())) {
                    Employee employee = employeeDAO.selectEmployeeById(Integer.parseInt(idField.getText()));
                    if (employee != null) {
                        fillFields(employee);
                    } else {
                        resultArea.setText("Employee not found.");
                    }
                } else {
                    resultArea.setText("Please enter a valid numeric ID.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID format.");
            }
        });

        languageButton.addActionListener(e -> switchLanguage(languageButton));

        frame.setVisible(true);
    }

    // Agrega el icono de información a los campos de texto
    private void addInfoIcon(JComponent component, String message) {
        JLabel infoLabel = new JLabel("\u24D8");  // Unicode character for ⓘ
        infoLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22)); // Reducir el tamaño de fuente
        infoLabel.setForeground(Color.BLUE);
        infoLabel.setBounds(1744, component.getBounds().y, 24, 42); // Ajustar el tamaño y la posición del ícono
        infoLabel.setToolTipText(message);
        frame.getContentPane().add(infoLabel);
    }

    // Valida los campos de entrada
    private boolean validateFields() {
        if (!isNumeric(idField.getText())) {
            JOptionPane.showMessageDialog(frame, "ID must be numeric.");
            return false;
        }
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
            return false;
        }
        if (addressField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Address cannot be empty.");
            return false;
        }
        if (!isValidPhone(phoneField.getText())) {
            JOptionPane.showMessageDialog(frame, "Phone number is not valid.");
            return false;
        }
        if (!isValidEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(frame, "Email is not valid.");
            return false;
        }
        if (!isValidDate(birthdateField.getText())) {
            JOptionPane.showMessageDialog(frame, "Birthdate is not valid. Use format ddMMyyyy.");
            return false;
        }
        return true;
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isValidPhone(String str) {
        return str.matches("\\d{10}");
    }

    private boolean isValidEmail(String str) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).matcher(str).find();
    }

    private boolean isValidDate(String str) {
        if (str.length() != 8) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate.parse(str, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Método para limpiar los campos de entrada y el área de resultados
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        birthdateField.setText("");
        genderComboBox.setSelectedIndex(0);
        resultArea.setText("");
    }

    // Método para rellenar los campos con los datos del empleado
    private void fillFields(Employee employee) {
        idField.setText(String.valueOf(employee.getId()));
        nameField.setText(employee.getName());
        addressField.setText(employee.getAddress());
        phoneField.setText(employee.getPhone());
        emailField.setText(employee.getEmail());
        birthdateField.setText(employee.getBirthdate().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        genderComboBox.setSelectedItem(employee.getGender());
    }

    // Cambia el idioma de los componentes de la interfaz
    private void switchLanguage(JButton languageButton) {
        boolean isEnglish = languageButton.getText().equals("Español");

        if (isEnglish) {
            languageButton.setText("English");
            idLabel.setText("Identificador:");
            nameLabel.setText("Nombre:");
            addressLabel.setText("Dirección:");
            phoneLabel.setText("Teléfono:");
            emailLabel.setText("Correo:");
            birthdateLabel.setText("Fecha de Nacimiento:");
            genderLabel.setText("Género:");
            insertButton.setText("Insertar");
            updateButton.setText("Actualizar");
            deleteButton.setText("Eliminar");
            selectButton.setText("Seleccionar");
            searchButton.setText("Buscar");
        } else {
            languageButton.setText("Español");
            idLabel.setText("ID:");
            nameLabel.setText("Full Name:");
            addressLabel.setText("Full Address:");
            phoneLabel.setText("Phone:");
            emailLabel.setText("Email:");
            birthdateLabel.setText("Birthdate:");
            genderLabel.setText("Gender:");
            insertButton.setText("Insert");
            updateButton.setText("Update");
            deleteButton.setText("Delete");
            selectButton.setText("Select");
            searchButton.setText("Search");
        }
    }

    // Métodos para manejar las operaciones CRUD
    private void insertEmployee() {
        Employee employee = new Employee();
        employee.setName(nameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhone(phoneField.getText());
        employee.setEmail(emailField.getText());
        employee.setBirthdate(convertToDate(birthdateField.getText()));
        employee.setGender(genderComboBox.getSelectedItem().toString());
        employeeDAO.insertEmployee(employee);
    }

    private void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(idField.getText()));
        employee.setName(nameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhone(phoneField.getText());
        employee.setEmail(emailField.getText());
        employee.setBirthdate(convertToDate(birthdateField.getText()));
        employee.setGender(genderComboBox.getSelectedItem().toString());
        employeeDAO.updateEmployee(employee);
    }

    private void deleteEmployee() {
        int id = Integer.parseInt(idField.getText());
        employeeDAO.deleteEmployee(id);
    }

    private List<Employee> selectEmployees() {
        return employeeDAO.selectAllEmployees();
    }

    private LocalDate convertToDate(String dateStr) {
        if (dateStr.length() != 8) {
            throw new DateTimeParseException("Invalid date format", dateStr, 0);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new LoginFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
