package main.java.views;

import main.java.dao.EmployeeDAO;
import main.java.models.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EmployeeManagementFrame extends JFrame {
    private JTextField idField, nameField, addressField, phoneField, emailField;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox, genderComboBox;
    private JButton insertButton, updateButton, deleteButton, selectButton, searchButton, languageButton;
    private JLabel idLabel, nameLabel, addressLabel, phoneLabel, emailLabel, birthdateLabel, genderLabel;
    private JTextArea resultArea;
    private EmployeeDAO employeeDAO;

    private String[] gendersEnglish = {"Male", "Female", "Other"};
    private String[] gendersSpanish = {"Masculino", "Femenino", "Otro"};
    private boolean isEnglish = true;

    private Map<JComponent, JLabel> infoIconMap;

    public EmployeeManagementFrame() {
        employeeDAO = new EmployeeDAO();
        infoIconMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setTitle("Employee Management System - Carlos Padilla");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Use GradientPanel instead of regular JPanel
        GradientPanel contentPanel = new GradientPanel(null);
        contentPanel.setGradientColors(new Color(122, 71, 222), new Color(2, 222, 255));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Obtener las dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Margenes
        int margin = 20;
        int labelWidth = 200;
        int fieldWidth = screenWidth - labelWidth - margin * 4;

        // Configurar etiquetas y campos de texto
        Font font = new Font("Roboto", Font.PLAIN, 28);
        Font labelFont = new Font("Roboto", Font.BOLD, 22);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(margin, margin, labelWidth, 30);
        idLabel.setFont(labelFont);
        idLabel.setForeground(Color.WHITE);
        getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(labelWidth + margin * 2, margin, (int) (fieldWidth * 0.08), 30);
        idField.setFont(font);
        getContentPane().add(idField);
        addInfoIcon(idField, "Enter your numeric ID");

        searchButton = new JButton("Search");
        searchButton.setBounds(screenWidth - margin * 2 - 1465, margin, 135, 30);
        searchButton.setBackground(new Color(75, 0, 130));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Roboto", Font.BOLD, 22));
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        getContentPane().add(searchButton);

        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(margin, margin * 2 + 30, labelWidth, 30);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.WHITE);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(labelWidth + margin * 2, margin * 2 + 30, fieldWidth, 30);
        nameField.setFont(font);
        getContentPane().add(nameField);
        addInfoIcon(nameField, "Enter your full name");

        addressLabel = new JLabel("Full Address:");
        addressLabel.setBounds(margin, margin * 3 + 60, labelWidth, 30);
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(Color.WHITE);
        getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(labelWidth + margin * 2, margin * 3 + 60, fieldWidth, 30);
        addressField.setFont(font);
        getContentPane().add(addressField);
        addInfoIcon(addressField, "Enter your full address");

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(margin, margin * 4 + 90, labelWidth, 30);
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(Color.WHITE);
        getContentPane().add(phoneLabel);

        phoneField = new JTextField("52");
        phoneField.setBounds(labelWidth + margin * 2, margin * 4 + 90, fieldWidth, 30);
        phoneField.setFont(font);
        getContentPane().add(phoneField);
        addInfoIcon(phoneField, "Enter phone number (example: 525555432112)");

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(margin, margin * 5 + 120, labelWidth, 30);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.WHITE);
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(labelWidth + margin * 2, margin * 5 + 120, fieldWidth, 30);
        emailField.setFont(font);
        getContentPane().add(emailField);
        addInfoIcon(emailField, "Enter email address (user@email.com)");

        birthdateLabel = new JLabel("Birthdate:");
        birthdateLabel.setBounds(margin, margin * 6 + 150, labelWidth, 30);
        birthdateLabel.setFont(labelFont);
        birthdateLabel.setForeground(Color.WHITE);
        getContentPane().add(birthdateLabel);

        dayComboBox = new JComboBox<>(generateNumbers(1, 31));
        dayComboBox.setBounds(labelWidth + margin * 2, margin * 6 + 150, (int) (fieldWidth * 0.06), 30);
        dayComboBox.setFont(font);
        getContentPane().add(dayComboBox);

        monthComboBox = new JComboBox<>(generateNumbers(1, 12));
        monthComboBox.setBounds(labelWidth + margin * 2 + (int) (fieldWidth * 0.07), margin * 6 + 150, (int) (fieldWidth * 0.06), 30);
        monthComboBox.setFont(font);
        getContentPane().add(monthComboBox);

        yearComboBox = new JComboBox<>(generateNumbers(1900, LocalDate.now().getYear()));
        yearComboBox.setBounds(labelWidth + margin * 2 + (int) (fieldWidth * 0.14), margin * 6 + 150, (int) (fieldWidth * 0.08), 30);
        yearComboBox.setFont(font);
        getContentPane().add(yearComboBox);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(margin, margin * 7 + 180, labelWidth, 30);
        genderLabel.setFont(labelFont);
        genderLabel.setForeground(Color.WHITE);
        getContentPane().add(genderLabel);

        genderComboBox = new JComboBox<>(gendersEnglish);
        genderComboBox.setBounds(labelWidth + margin * 2, margin * 7 + 180, (int) (fieldWidth * 0.10), 30);
        genderComboBox.setFont(font);
        getContentPane().add(genderComboBox);
        addInfoIcon(genderComboBox, "Select gender");

        // Configurar botones
        int buttonWidth = (screenWidth - margin * 3) / 5;
        insertButton = createStyledButton("Add Employee");
        insertButton.setBounds(margin, margin * 8 + 210, buttonWidth, 40);
        getContentPane().add(insertButton);

        updateButton = createStyledButton("Update employee");
        updateButton.setBounds(margin * 2 + buttonWidth, margin * 8 + 210, buttonWidth, 40);
        getContentPane().add(updateButton);

        deleteButton = createStyledButton("Delete employee");
        deleteButton.setBounds(margin * 3 + buttonWidth * 2, margin * 8 + 210, buttonWidth, 40);
        getContentPane().add(deleteButton);

        selectButton = createStyledButton("View Employees");
        selectButton.setBounds(margin * 4 + buttonWidth * 3, margin * 8 + 210, buttonWidth, 40);
        getContentPane().add(selectButton);

        languageButton = createStyledButton("Español");
        languageButton.setBounds(margin * 5 + buttonWidth * 4, margin * 8 + 210, (int) (buttonWidth * 0.85), 40);
        getContentPane().add(languageButton);

        // Configurar área de resultados
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 28)); // Cambié el tamaño de 19 a 28
        resultArea.setBackground(new Color(255, 228, 225));
        resultArea.setForeground(Color.BLACK);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(margin, margin * 9 + 250, screenWidth - margin * 2, screenHeight - margin * 10 - 250);
        getContentPane().add(scrollPane);

        // Listeners para los botones
        insertButton.addActionListener(e -> {
            try {
                if (validateFields()) {
                    insertEmployee();
                    resultArea.setText(isEnglish ? "Employee Inserted!" : "Empleado Agregado!");
                    clearFields();
                } else {
                    resultArea.setText(isEnglish ? "Error: Invalid input data." : "Error: Datos de entrada no válidos.");
                }
            } catch (DateTimeParseException ex) {
                resultArea.setText(isEnglish ? "Error: Invalid date format. Use ddMMyyyy." : "Error: Formato de fecha no válido. Use ddMMyyyy.");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                if (validateFields()) {
                    updateEmployee();
                    resultArea.setText(isEnglish ? "Employee Updated!" : "Empleado Actualizado!");
                    clearFields();
                } else {
                    resultArea.setText(isEnglish ? "Error: Invalid input data." : "Error: Datos de entrada no válidos.");
                }
            } catch (DateTimeParseException ex) {
                resultArea.setText(isEnglish ? "Error: Invalid date format. Use ddMMyyyy." : "Error: Formato de fecha no válido. Use ddMMyyyy.");
            }
        });

        deleteButton.addActionListener(e -> {
            deleteEmployee();
            resultArea.setText(isEnglish ? "Employee Deleted!" : "Empleado Eliminado!");
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
                        resultArea.setText(isEnglish ? "Employee not found." : "Empleado no encontrado.");
                    }
                } else {
                    resultArea.setText(isEnglish ? "Please enter a valid numeric ID." : "Por favor, ingrese un identificador numérico válido.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText(isEnglish ? "Invalid ID format." : "Formato de identificador (ID) no válido.");
            }
        });

        languageButton.addActionListener(e -> switchLanguage());

        // Agregar acceso de teclado para cerrar la ventana
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

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

    // Genera un array de enteros como cadenas
    private String[] generateNumbers(int start, int end) {
        String[] numbers = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = String.valueOf(i);
        }
        return numbers;
    }

    // Agrega el icono de información a los campos de texto
    private void addInfoIcon(JComponent component, String message) {
        JLabel infoLabel = infoIconMap.get(component);
        if (infoLabel == null) {
            infoLabel = new JLabel("\u24D8"); // Unicode character for ⓘ
            infoLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
            infoLabel.setForeground(Color.BLUE);
            infoLabel.setToolTipText(message);
            infoLabel.setBounds(component.getX() + component.getWidth() + 5, component.getY(), 30, 30);
            getContentPane().add(infoLabel);
            infoIconMap.put(component, infoLabel);
        } else {
            infoLabel.setToolTipText(message);
        }
    }

    // Actualiza los mensajes de información
    private void updateInfoIcons() {
        addInfoIcon(idField, isEnglish ? "Ingrese su ID numérico" : "Enter your numeric ID");
        addInfoIcon(nameField, isEnglish ? "Ingrese su nombre completo" : "Enter your full name");
        addInfoIcon(addressField, isEnglish ? "Ingrese su dirección completa" : "Enter your full address");
        addInfoIcon(phoneField, isEnglish ? "Ingrese número de teléfono (ejemplo: 525555432112)" : "Enter phone number (example: 525555432112)");
        addInfoIcon(emailField, isEnglish ? "Ingrese dirección de correo (usuario@correo.com)" : "Enter email address (user@email.com)");
        addInfoIcon(dayComboBox, isEnglish ? "Seleccione el día de nacimiento" : "Select birth day");
        addInfoIcon(monthComboBox, isEnglish ? "Seleccione el mes de nacimiento" : "Select birth month");
        addInfoIcon(yearComboBox, isEnglish ? "Seleccione el año de nacimiento" : "Select birth year");
        addInfoIcon(genderComboBox, isEnglish ? "Seleccione género" : "Select gender");
    }

    // Valida los campos de entrada
    private boolean validateFields() {
        if (!isNumeric(idField.getText())) {
            JOptionPane.showMessageDialog(this, isEnglish ? "ID must be numeric." : "El ID debe ser numérico.");
            return false;
        }
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, isEnglish ? "Name cannot be empty." : "El nombre no puede estar vacío.");
            return false;
        }
        if (addressField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, isEnglish ? "Address cannot be empty." : "La dirección no puede estar vacía.");
            return false;
        }
        if (!isValidPhone(phoneField.getText())) {
            JOptionPane.showMessageDialog(this, isEnglish ? "Phone number is not valid." : "El número de teléfono no es válido.");
            return false;
        }
        if (!isValidEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(this, isEnglish ? "Email is not valid." : "El correo electrónico no es válido.");
            return false;
        }
        if (!isValidDate()) {
            JOptionPane.showMessageDialog(this, isEnglish ? "Birthdate is not valid." : "La fecha de nacimiento no es válida.");
            return false;
        }
        return true;
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isValidPhone(String str) {
        return str.matches("52\\d{10}");
    }

    private boolean isValidEmail(String str) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE).matcher(str).find();
    }

    private boolean isValidDate() {
        try {
            LocalDate.of(
                    Integer.parseInt((String) yearComboBox.getSelectedItem()),
                    Integer.parseInt((String) monthComboBox.getSelectedItem()),
                    Integer.parseInt((String) dayComboBox.getSelectedItem())
            );
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
        phoneField.setText("52");
        emailField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
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
        LocalDate birthdate = employee.getBirthdate();
        dayComboBox.setSelectedItem(String.valueOf(birthdate.getDayOfMonth()));
        monthComboBox.setSelectedItem(String.valueOf(birthdate.getMonthValue()));
        yearComboBox.setSelectedItem(String.valueOf(birthdate.getYear()));
        genderComboBox.setSelectedItem(isEnglish ? employee.getGender() : translateGenderToSpanish(employee.getGender()));
    }

    // Traducir género a español
    private String translateGenderToSpanish(String gender) {
        switch (gender) {
            case "Male":
                return "Masculino";
            case "Female":
                return "Femenino";
            case "Other":
                return "Otro";
            default:
                return gender;
        }
    }

    // Cambia el idioma de los componentes de la interfaz
    private void switchLanguage() {
        if (isEnglish) {
            languageButton.setText("English");
            idLabel.setText("Identificador:");
            nameLabel.setText("Nombre:");
            addressLabel.setText("Dirección:");
            phoneLabel.setText("Teléfono:");
            emailLabel.setText("Correo:");
            birthdateLabel.setText("Nacimiento fecha:");
            genderLabel.setText("Género:");
            insertButton.setText("Agregar empleado");
            updateButton.setText("Actualizar información");
            deleteButton.setText("Eliminar empleado");
            selectButton.setText("Ver empleados");
            searchButton.setText("Buscar");

            genderComboBox.setModel(new DefaultComboBoxModel<>(gendersSpanish));
            updateInfoIcons();

            isEnglish = false;
        } else {
            languageButton.setText("Español");
            idLabel.setText("ID:");
            nameLabel.setText("Full Name:");
            addressLabel.setText("Full Address:");
            phoneLabel.setText("Phone:");
            emailLabel.setText("Email:");
            birthdateLabel.setText("Birthdate:");
            genderLabel.setText("Gender:");
            insertButton.setText("Add employee");
            updateButton.setText("Update employee");
            deleteButton.setText("Delete employee");
            selectButton.setText("View employees");
            searchButton.setText("Search");

            genderComboBox.setModel(new DefaultComboBoxModel<>(gendersEnglish));
            updateInfoIcons();

            isEnglish = true;
        }
    }

    // Métodos para manejar las operaciones CRUD
    private void insertEmployee() {
        Employee employee = new Employee();
        employee.setName(nameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhone(phoneField.getText());
        employee.setEmail(emailField.getText());
        employee.setBirthdate(convertToDate());
        employee.setGender(isEnglish ? genderComboBox.getSelectedItem().toString() : translateGenderToEnglish(genderComboBox.getSelectedItem().toString()));
        employeeDAO.insertEmployee(employee);
    }

    private void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(idField.getText()));
        employee.setName(nameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhone(phoneField.getText());
        employee.setEmail(emailField.getText());
        employee.setBirthdate(convertToDate());
        employee.setGender(isEnglish ? genderComboBox.getSelectedItem().toString() : translateGenderToEnglish(genderComboBox.getSelectedItem().toString()));
        employeeDAO.updateEmployee(employee);
    }

    private void deleteEmployee() {
        int id = Integer.parseInt(idField.getText());
        employeeDAO.deleteEmployee(id);
    }

    private List<Employee> selectEmployees() {
        return employeeDAO.selectAllEmployees();
    }

    private LocalDate convertToDate() {
        int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem());
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
        return LocalDate.of(year, month, day);
    }

    // Traducir género a inglés
    private String translateGenderToEnglish(String gender) {
        switch (gender) {
            case "Masculino":
                return "Male";
            case "Femenino":
                return "Female";
            case "Otro":
                return "Other";
            default:
                return gender;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagementFrame::new);
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
