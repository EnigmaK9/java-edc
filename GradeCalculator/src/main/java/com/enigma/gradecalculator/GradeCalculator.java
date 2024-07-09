package com.enigma.gradecalculator;

/**
 *
 * @author Carlos Ignacio Padilla Herrera
 */
import java.util.Scanner;20

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Fixed percentages for each component
        double practicesPercentage = 0.5;
        double exercisesPercentage = 0.2;
        double examsPercentage = 0.3;
        double finalProjectPercentage = 0.4;
        
        // Prompt the user to input grades for each component
        System.out.print("Enter the grade for Practice 1 (as a percentage, e.g., 20 for 20%): ");
        double practice1Grade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for Practice 2 (as a percentage, e.g., 30 for 30%): ");
        double practice2Grade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for Exercise 1 (as a percentage, e.g., 10 for 10%): ");
        double exercise1Grade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for Exercise 2 (as a percentage, e.g., 10 for 10%): ");
        double exercise2Grade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for Diagnostic Exam (as a percentage, e.g., 0 for 0%): ");
        double diagnosticExamGrade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for Final Exam (as a percentage, e.g., 30 for 30%): ");
        double finalExamGrade = scanner.nextDouble() / 100;
        
        System.out.print("Enter the grade for the Final Project (as a percentage, e.g., 100 for 100%): ");
        double finalProjectGrade = scanner.nextDouble() / 100;
        
        // Calculate the final grade
        double finalGrade = (practicesPercentage * (practice1Grade + practice2Grade)) +
                            (exercisesPercentage * (exercise1Grade + exercise2Grade)) +
                            (examsPercentage * (diagnosticExamGrade + finalExamGrade)) +
                            (finalProjectPercentage * finalProjectGrade);
        
        // Output the final grade
        System.out.println("Final Grade: " + (finalGrade * 100) + "%");
        
        scanner.close();
    }
}

