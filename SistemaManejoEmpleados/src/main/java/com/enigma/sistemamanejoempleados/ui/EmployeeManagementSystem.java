/*
 * -----------------------------------------------------------------------------
 *  Author: Carlos Ignacio Padilla Herrera
 *  Project: Employee Management System
 *  Description: User Interface class for managing employee operations.
 * -----------------------------------------------------------------------------
 */

package com.enigma.sistemamanejoempleados.ui;

import com.enigma.sistemamanejoempleados.dao.EmployeeDAO;
import com.enigma.sistemamanejoempleados.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
