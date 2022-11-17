import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Student {

    private int id;
    private String firstName, lastName, course;
    private List<Subject> enlistedSubjects;

    private static List<Student> listOfStudents = new ArrayList<Student>();
    private static List<Integer> listOfStudentId = new ArrayList<Integer>();

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public List<Subject> getEnlistedSubjects() {
        return enlistedSubjects;
    }

    public static List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public static List<Integer> getListOfStudentId() {
        return listOfStudentId;
    }

    public Student(int id, String firstName, String lastName, String course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.enlistedSubjects = new ArrayList<Subject>();
    }

    public void EnlistSubject(Subject subject) {
        if (this.getEnlistedSubjects().contains(subject)) 
            out.println("\nStudent is already enlisted in this subject.\n");
        else {
            this.getEnlistedSubjects().add(subject); 
            subject.getEnlistedStudents().add(this);
            out.println("\nStudent enlisted in subject successfully.\n"); 
        }
    }

    public void RemoveSubject(Subject subject) {
        if (this.getEnlistedSubjects().contains(subject)) {
            this.getEnlistedSubjects().remove(subject); 
            subject.getEnlistedStudents().remove(this);
            out.println("\nStudent removed from subject successfully.\n"); 
        } else 
            out.println("\nStudent is not enlisted in this subject.\n");
    }

    public void ViewStudentDetails() {
        if (this.getEnlistedSubjects().isEmpty()) 
            out.printf("%s%n%-16d%-26s%-16s%s%n", "-".repeat(101), this.getId(), this.getFirstName() + " " + this.getLastName(), this.getCourse(), "None");
        else
            out.printf("%s%n%-16s%-26s%-16s%-6d%-39s%n", "-".repeat(101), this.getId(), this.getFirstName() + " " + this.getLastName(), this.getCourse(), this.getEnlistedSubjects().get(0).getId(), this.getEnlistedSubjects().get(0).getSubjectName());

        for (int index = 1; index < this.getEnlistedSubjects().size(); index++) {
            out.printf("%s%-6d%s%n", " ".repeat(58), this.getEnlistedSubjects().get(index).getId(), this.getEnlistedSubjects().get(index).getSubjectName());
        }
    }

    public void ViewAvailableSubjects() {
        out.printf("%n%s%n%44s%n%s%n", "=".repeat(60), "AVAILABLE SUBJECTS TO ENLIST", "=".repeat(60));
        out.printf("%-8s%-40s%-8s%n", "ID", "SUBJECT NAME", "UNITS");

        for (Subject subject : Subject.getListOfSubjects()) {

            if (!this.getEnlistedSubjects().contains(subject)) 
                out.printf("%s%n%-8s%-40s%-8s%n", "-".repeat(60), subject.getId(), subject.getSubjectName(), subject.getUnits());
        }

        out.println("=".repeat(60) + "\n");
    }
}
