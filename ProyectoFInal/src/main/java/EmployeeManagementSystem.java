package main.java;

import main.java.views.LoginFrame;
import java.awt.EventQueue;

public class EmployeeManagementSystem {
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
