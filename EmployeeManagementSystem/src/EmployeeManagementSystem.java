import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

// Clase DatabaseUtil
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

// Clase EmployeeDAO
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

// Clase Principal EmployeeManagementSystem
public class EmployeeManagementSystem {
    private JFrame frame;
    private JTextField idField, nameField, addressField, phoneField, emailField, birthdateField;
    private JComboBox<String> genderComboBox;
    private JButton insertButton, updateButton, deleteButton, selectButton;
    private EmployeeDAO employeeDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeManagementSystem window = new EmployeeManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EmployeeManagementSystem() {
        employeeDAO = new EmployeeDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10, 10, 80, 25);
        frame.getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 10, 160, 25);
        frame.getContentPane().add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 40, 80, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 40, 160, 25);
        frame.getContentPane().add(nameField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(10, 70, 80, 25);
        frame.getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(100, 70, 160, 25);
        frame.getContentPane().add(addressField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(10, 100, 80, 25);
        frame.getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100, 100, 160, 25);
        frame.getContentPane().add(phoneField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 130, 80, 25);
        frame.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 130, 160, 25);
        frame.getContentPane().add(emailField);

        JLabel birthdateLabel = new JLabel("Birthdate");
        birthdateLabel.setBounds(10, 160, 80, 25);
        frame.getContentPane().add(birthdateLabel);

        birthdateField = new JTextField();
        birthdateField.setBounds(100, 160, 160, 25);
        frame.getContentPane().add(birthdateField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(10, 190, 80, 25);
        frame.getContentPane().add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setBounds(100, 190, 160, 25);
        frame.getContentPane().add(genderComboBox);

        insertButton = new JButton("Insert");
        insertButton.setBounds(10, 230, 80, 25);
        frame.getContentPane().add(insertButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 230, 80, 25);
        frame.getContentPane().add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(190, 230, 80, 25);
        frame.getContentPane().add(deleteButton);

        selectButton = new JButton("Select");
        selectButton.setBounds(280, 230, 80, 25);
        frame.getContentPane().add(selectButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 260, 400, 50);
        frame.getContentPane().add(resultArea);

        insertButton.addActionListener(e -> {
            try {
                insertEmployee();
                resultArea.setText("Employee Inserted!");
            } catch (DateTimeParseException ex) {
                resultArea.setText("Error: Invalid date format. Use ddMMyyyy.");
            }
        });
        updateButton.addActionListener(e -> {
            try {
                updateEmployee();
                resultArea.setText("Employee Updated!");
            } catch (DateTimeParseException ex) {
                resultArea.setText("Error: Invalid date format. Use ddMMyyyy.");
            }
        });
        deleteButton.addActionListener(e -> {
            deleteEmployee();
            resultArea.setText("Employee Deleted!");
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
    }

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
}
