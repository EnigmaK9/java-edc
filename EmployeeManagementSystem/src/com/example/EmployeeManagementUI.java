package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class EmployeeManagementUI {
    private JFrame frame;
    private JTextField idField, nameField, addressField, phoneField, emailField;
    private JFormattedTextField birthdateField;
    private JComboBox<String> genderComboBox;
    private JButton insertButton, updateButton, deleteButton, selectButton;
    private EmployeeDAO employeeDAO;

    public EmployeeManagementUI() {
        employeeDAO = new EmployeeDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
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

        birthdateField = new JFormattedTextField();
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
        frame.getContentPane().add(select
