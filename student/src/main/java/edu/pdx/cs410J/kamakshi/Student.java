package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is represents a <code>Student</code>.
 */
public class Student extends Human {

    static final String USAGE_MESSAGE = "args are (in this order)\n" +
            "  name \n" +
            "  gender \n" +
            "  gpa \n" +
            "  classes";
    static final String MISSING_GPA = "Missing GPA";

    private final double gpa;
    private final String gender;
    private final List<String> classes;

    /**
     * Creates a new <code>Student</code>
     *
     * @param name
     *        The student's name
     * @param classes
     *        The names of the classes the student is taking.  A student
     *        may take zero or more classes.
     * @param gpa
     *        The student's grade point average
     * @param gender
     *        The student's gender ("male" or "female", case insensitive)
     *
     * @throws IllegalStudentArgumentException
     *         GPA is negative
     */
    public Student(String name, List<String> classes, double gpa, String gender)
            throws IllegalStudentArgumentException {

        super(name);

        if (gpa < 0.0) {
            throw new IllegalStudentArgumentException("Invalid GPA: " + gpa);
        }

        this.gpa = gpa;

        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
            this.gender = gender;

        } else {
            throw new IllegalStudentArgumentException("Invalid gender: " + gender);
        }

        this.classes = classes;

    }

    /**
     * All students say "This class is too much work"
     */
    @Override
    public String says() {
        return "This class is too much work";
    }

    /**
     * Returns a <code>String</code> that describes this
     * <code>Student</code>.
     */
    public String toString() {
        return name + " has a GPA of " + gpa + " and is taking " + getDescriptionOfClasses() + ".  " +
                getGenderBasedPronoun() + " says \"" + says() + "\".";
    }

    private String getGenderBasedPronoun() {
        if (this.gender.equalsIgnoreCase("male")) {
            return "He";

        } else {
            return "She";
        }
    }

    private String getDescriptionOfClasses() {
        switch (this.classes.size()) {
            case 0:
                return "0 classes";

            case 1:
                return "1 class: " + this.classes.get(0);

            default:
                StringBuilder sb = new StringBuilder();
                sb.append(this.classes.size());
                sb.append(" classes: ");

                for (int i = 0; i < this.classes.size(); i++) {
                    String c = this.classes.get(i);
                    sb.append(c);

                    if (i != this.classes.size() - 1) {
                        sb.append(", ");
                    }

                    if (i == this.classes.size() - 2) {
                        sb.append("and ");
                    }

                }

                return sb.toString();
        }
    }

    /**
     * Main program that parses the command line, creates a
     * <code>Student</code>, and prints a description of the student to
     * standard out by invoking its <code>toString</code> method.
     */
    public static void main(String[] args) {

        String name = null;
        List<String> classes = new ArrayList<>();
        double gpa = 0.0;
        String gender = null;

        switch (args.length) {
            case 0:
                printErrorMessageAndExit("Missing command line arguments");
                break;

            case 1:
                printErrorMessageAndExit("Missing Gender");
                break;

            case 2:
                printErrorMessageAndExit(MISSING_GPA);
                break;

            default:
                name = args[0];
                gender = args[1];
                gpa = Double.parseDouble(args[2]);

                for(int i = 3; i < args.length; i++) {
                    classes.add(args[i]);
                }
        }

        printStudentDescriptionToStandardOut(name, classes, gpa, gender);
    }

    private static void printStudentDescriptionToStandardOut(String name, List<String> classes, double gpa, String gender) {
        Student student;
        try {
            student = new Student(name, classes, gpa, gender);

        } catch (IllegalStudentArgumentException ex) {
            printErrorMessageAndExit(ex.getMessage());
            return;
        }

        System.out.print(student.toString());
        System.exit(0);
    }

    private static void printErrorMessageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println(USAGE_MESSAGE);
        System.exit(1);
    }
}