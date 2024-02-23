package ibm.com.Filehandle;

import java.util.Scanner;

import Filehandling.XmlToJsonConverter;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hardcoded file path for the XML file
        String filePath = "C:\\Users\\MaitreyeeMitra\\eclipse-workspace\\Filehandle\\src\\main\\java\\Filehandling\\student.xml";

        // Prompting the user to choose the output format (JSON or XML)
        System.out.println("Choose the output format:");
        System.out.println("1. JSON");
        System.out.println("2. XML");
        int choice = scanner.nextInt();
        System.out.println("User input - Output format choice: " + choice);

        // Prompting the user to enter the student ID
        System.out.println("Enter the student ID:");
        int studentId = scanner.nextInt();
        System.out.println("User input - Student ID: " + studentId);

        // Creating an instance of XmlToJsonConverter
        XmlToJsonConverter converter = new XmlToJsonConverter();

        // Read XML file and print details of the student with the specified ID
        converter.printStudentDetails(filePath, studentId, choice);

        scanner.close();
    }
}
