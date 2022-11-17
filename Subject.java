import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Subject {

    private int id, units;
    private String subjectName;
    private List<Student> enlistedStudents;

    private static List<Subject> listOfSubjects = new ArrayList<Subject>();
    private static List<Integer> listOfSubjectId = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getUnits() {
        return units;
    }

    public List<Student> getEnlistedStudents() {
        return enlistedStudents;
    }

    public static List<Subject> getListOfSubjects() {
        return listOfSubjects;
    }

    public static List<Integer> getListOfSubjectId() {
        return listOfSubjectId;
    }

    public Subject(int id, String subjectName, int units) {
        this.id = id;
        this.subjectName = subjectName;
        this.units = units;
        this.enlistedStudents = new ArrayList<Student>();
    }

    public static void ViewAllSubjects() {
        out.printf("%n%s%n%39s%n%s%n", "=".repeat(60), "LIST OF SUBJECTS", "=".repeat(60));
        out.printf("%-8s%-40s%-8s%n", "ID", "SUBJECT NAME", "UNITS");

        for (Subject subject : Subject.getListOfSubjects()) {
             out.printf("%s%n%-8s%-40s%-8s%n", "-".repeat(60), subject.getId(), subject.getSubjectName(), subject.getUnits());
        }

        out.println("=".repeat(60) + "\n");
    }

    public void ViewEnlistedStudents() {

        if (this.getEnlistedStudents().isEmpty()) 
            out.println("\nNo students are enlisted in this subject.\n");
        else {
            out.printf("%n%s%n%47s%n%s", "=".repeat(60), "ENLISTED STUDENTS IN SUBJECT ID " + this.getId(), "=".repeat(60));
            out.printf("%n%-15s %-30s %-7s%n", "STUDENT ID", "FULLNAME", "COURSE");
    
            for (Student student : this.getEnlistedStudents()) {
                out.printf("%s%n%-15d %-30s %-7s%n", "-".repeat(60), student.getId(), student.getFirstName() + " " + student.getLastName(), student.getCourse());
            }

            out.println("=".repeat(60) + "\n");
        }
    }
}
