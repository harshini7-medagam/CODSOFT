import java.io.*;
import java.util.*;

class Student implements Serializable {

    private String name;
    private int rollNumber;
    private String grade;
    private String department;

    public Student(String name, int rollNumber, String grade, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.department = department;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber +
               ", Name: " + name +
               ", Grade: " + grade +
               ", Department: " + department;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.dat";

    // Add Student
    static void addStudent(Student s) {
        students.add(s);
        System.out.println("Student Added Successfully!");
    }

    // Remove Student
    static void removeStudent(int roll) {
        Student s = searchStudent(roll);

        if (s != null) {
            students.remove(s);
            System.out.println("Student Removed Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    // Search Student
    static Student searchStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                return s;
            }
        }
        return null;
    }

    // Display All Students
    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }

        System.out.println("\n------ Student List ------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Edit Student
    static void editStudent(int roll, String name, String grade, String dept) {
        Student s = searchStudent(roll);

        if (s != null) {
            s.setName(name);
            s.setGrade(grade);
            s.setDepartment(dept);
            System.out.println("Student Updated Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    // Save Data to File
    static void saveData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(students);
            out.close();
            System.out.println("Data Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error Saving Data.");
        }
    }

    // Load Data from File
    @SuppressWarnings("unchecked")
    static void loadData() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("No Previous Data Found.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        loadData();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if (grade.trim().isEmpty()) {
                        System.out.println("Grade cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    addStudent(new Student(name, roll, grade, dept));
                    break;

                case 2:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();
                    removeStudent(roll);
                    break;

                case 3:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();

                    Student s = searchStudent(roll);

                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 4:
                    displayStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    name = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    grade = sc.nextLine();

                    System.out.print("Enter New Department: ");
                    dept = sc.nextLine();

                    editStudent(roll, name, grade, dept);
                    break;

                case 6:
                    saveData();
                    break;

                case 7:
                    saveData();
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}