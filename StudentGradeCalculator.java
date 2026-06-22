import java.util.Scanner;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int n = sc.nextInt();
        int totalMarks = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter marks for Subject " + i + " (out of 100): ");
            int marks = sc.nextInt();
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter marks between 0 and 100.");
                return;
            }
            totalMarks += marks;
        }
        double averagePercentage = (double) totalMarks / n;
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }
        System.out.println("\n----- RESULT -----");
        System.out.println("Total Marks = " + totalMarks);
        System.out.println("Average Percentage = " + averagePercentage + "%");
        System.out.println("Grade = " + grade);
        sc.close();
    }
}