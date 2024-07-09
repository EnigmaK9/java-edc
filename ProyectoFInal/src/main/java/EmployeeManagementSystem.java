package main.java;

import main.java.views.LoginFrame;
import main.java.dao.EmployeeDAO;
import main.java.models.Employee;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeManagementSystem {
    public JFrame frame;  // Cambiado a público
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
        addInfoIcon(phoneField, "Enter phone number (example: 525555432112)");

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

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
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
        JLabel infoLabel = new JLabel("\u24D8"); // Unicode character for ⓘ
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
        return str.matches("\\d{12}");
    }

    private boolean isValidEmail(String str) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE).matcher(str).find();
    }

    private boolean isValidDate(String str) {
        if (str.length() != 8)
            return false;
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
