import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;

public class Main {

    static int id, units, choice;
    static String firstName, lastName, course, subjectName;
    static boolean isExisting;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            out.println("+----------------------------------------------+\n" +
                        "|              STUDENT ENLISTMENT              |\n" +
                        "+----------------------------------------------+\n" +
                        "| [1] Create new student                       |\n" +
                        "| [2] Create new subject                       |\n" +
                        "| [3] Enlist student in subject                |\n" +
                        "| [4] Remove student from subject              |\n" +
                        "| [5] View student details                     |\n" +
                        "| [6] View details of all students             |\n" +
                        "| [7] View all students enlisted in a subject  |\n" +
                        "| [8] EXIT                                     |\n" +
                        "+----------------------------------------------+\n");

            InputNumberOnly("your choice");

            switch (choice) {

                case 1:
                    do 
                        id = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                    while (Student.getListOfStudentId().indexOf(id) != -1);
                    
                    out.print("\n");
                    InputLettersOnly("first name"); 
                    InputLettersOnly("last name");
    
                    isExisting = false;

                    for (Student student : Student.getListOfStudents()) {
                        if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                            isExisting = true;
                            break;
                        }
                    }
    
                    if (isExisting) {
                        out.println("\nStudent already exists.\n");
                        break; 
                    }

                    InputLettersOnly("course");

                    Student newStudent = new Student(id, firstName, lastName, course);
                    Student.getListOfStudents().add(newStudent); 
                    Student.getListOfStudentId().add(id);

                    DisplayHeader("STUDENT INFORMATION");
                    newStudent.ViewStudentDetails();
                    out.println("=".repeat(101) + "\n");
                    out.println("New student record created successfully.\n");
                    break; 

                case 2:
                    do
                        id = (int) (Math.random() * (999 - 100 + 1) + 100);
                    while (Subject.getListOfSubjectId().indexOf(id) != -1);

                    out.print("\n");
                    InputLettersOnly("subject name");

                    isExisting = false;

                    for (Subject subject : Subject.getListOfSubjects()) {
                        if (subject.getSubjectName().equals(subjectName)) {
                            isExisting = true;
                            break;
                        }
                    }

                    if (isExisting) {
                        out.println("\nSubject already exists.\n");
                        break;
                    }

                    InputNumberOnly("units");

                    Subject.getListOfSubjects().add(new Subject(id, subjectName, units));
                    Subject.getListOfSubjectId().add(id);

                    out.println("\nNew subject record created successfully.\n");
                    break;

                case 3:

                    if (Student.getListOfStudents().isEmpty()) {
                        out.println("\nNo students available. Please create a student first.\n");
                        break;
                    }

                    out.print("\n");
                    InputNumberOnly("student id"); 

                    isExisting = false;
                    
                    for (Student student : Student.getListOfStudents()) {
                        if (student.getId() == id) {
                            if (student.getEnlistedSubjects().size() == Subject.getListOfSubjects().size()) {
                                out.println("\nStudent is already enlisted in all subjects.\n");
                                isExisting = true; 
                                break;
                            }

                            student.ViewAvailableSubjects();
                            
                            while (true) {
                                InputNumberOnly("subject id"); 
    
                                isExisting = false;

                                for (Subject subject : Subject.getListOfSubjects()) {
                                    if (subject.getId() == id) {
                                        student.EnlistSubject(subject);
                                        isExisting = true;
                                        break;
                                    }
                                }
    
                                if (isExisting) 
                                    break;
    
                                out.println("\nInvalid subject id. Please try again.\n");
                            }

                            break;
                        }
                    }

                    if (!isExisting) 
                        out.println("\nStudent does not exist.\n");

                    break;

                case 4:
                    if (Student.getListOfStudents().isEmpty()) {
                        out.println("\nNo students available. Please create a student first.\n");
                        break;
                    }

                    out.print("\n");
                    InputNumberOnly("student id");

                    isExisting = false;

                    for (Student student : Student.getListOfStudents()) {
                        if (student.getId() == id) {
                            if (student.getEnlistedSubjects().isEmpty()) {
                                out.println("\nStudent is not enlisted in any subject.\n");
                                isExisting = true; 
                                break;
                            }

                            DisplayHeader("STUDENT INFORMATION");

                            student.ViewStudentDetails();
                            out.println("=".repeat(101) + "\n");

                            while (true) {
                                InputNumberOnly("subject id");

                                isExisting = false;

                                for (Subject subject : Subject.getListOfSubjects()) {
                                    if (subject.getId() == id) {
                                        student.RemoveSubject(subject); 
                                        isExisting = true; 
                                        break; 
                                    }
                                }

                                if (isExisting) 
                                    break;
                                
                                out.println("\nInvalid subject id. Please try again.\n");
                            }

                            break;
                        }
                    }

                    if (!isExisting) 
                        out.println("\nStudent id does not exist.\n");

                    break;

                case 5:
                    if (Student.getListOfStudents().isEmpty()) {
                        out.println("\nNo students available. Please create a student first.\n");
                        break;
                    }

                    out.print("\n");
                    InputNumberOnly("student id"); 

                    isExisting = false; 

                    for (Student student : Student.getListOfStudents()) {
                        if (student.getId() == id) {
                            DisplayHeader("STUDENT INFORMATION");
                            student.ViewStudentDetails();
                            out.println("=".repeat(101) + "\n");
                            isExisting = true; 
                            break;
                        }
                    }

                    if (!isExisting) 
                        out.println("\nStudent id does not exist.\n");

                    break;

                case 6:
                    if (Student.getListOfStudents().isEmpty()) {
                        out.println("\nNo students available. Please create a student first.\n");
                        break;
                    }

                    DisplayHeader("LIST OF STUDENTS");

                    for (Student student : Student.getListOfStudents()) {
                        student.ViewStudentDetails(); 
                    }

                    out.println("=".repeat(101) + "\n");

                    break;

                case 7:
                    if (Subject.getListOfSubjects().isEmpty()) {
                        out.println("\nNo subjects available. Please create a subject first.\n");
                        break;
                    }

                    Subject.ViewAllSubjects();

                    while (true) {
                        InputNumberOnly("subject id"); 

                        isExisting = false;
    
                        for (Subject subject : Subject.getListOfSubjects()) {
                            if (subject.getId() == id) {
                                subject.ViewEnlistedStudents(); 
                                isExisting = true;
                                break;
                            }
                        }
    
                        if (isExisting) 
                           break;

                        out.println("\nInvalid subject id. Please try again.\n");
                    }

                    break;

                case 8:
                    System.out.println("\nSystem terminated.\n"); 
                    System.exit(0);
            }
        }
    }

    public static void InputNumberOnly(String info) throws IOException {
        int userInput; 

        while (true) {
            out.print("Enter " + info + ": "); 

            try {
                userInput = Integer.parseInt(input.readLine()); 
            } catch (NumberFormatException e) {
                out.println("\nPlease enter a number only!\n"); 
                continue;
            }

            if (info.equals("your choice")) {
                if (userInput < 1 || userInput > 8) {
                    out.println("\nInvalid choice. Please try again.\n");
                    continue;
                }
                    
                choice = userInput;
            }

            if (info.equals("student id") || info.equals("subject id")) 
                id = userInput;

            if (info.equals("units")) {
                if (userInput < 1) {
                    out.println("\nInvalid units. Please try again.\n");
                    continue;
                }

                units = userInput; 
            }

            break;
        }
    }

    public static void InputLettersOnly(String info) throws IOException {
        String userInput;

        while (true) {
            out.print("Enter " + info + ": "); 
            userInput = input.readLine(); 

            if (userInput.matches("[a-zA-Z\s]+") && !userInput.isBlank()) {
                if (info.equals("first name")) 
                    firstName = userInput.trim();

                if (info.equals("last name")) 
                    lastName = userInput.trim();

                if (info.equals("course")) 
                    course = userInput.trim();

                if (info.equals("subject name")) 
                    subjectName = userInput.trim();

                break;
            }

            if (userInput.isBlank()) 
                out.println("\nPlease enter a " + info + "!\n");
            else 
                out.println("\nPlease enter a letter only.\n");
        }
    }

    public static void DisplayHeader(String text) {
        String space;

        if (text.equals("STUDENT INFORMATION")) 
            space = "60";
        else 
           space = "58";

        out.printf("%n%s %n%" + space + "s%n%s", "=".repeat(101), text, "=".repeat(101));
        out.printf("%n%-15s %-25s %-15s %-40s%n", "STUDENT ID", "FULLNAME", "COURSE", "ENLISTED SUBJECTS");
    }
}
