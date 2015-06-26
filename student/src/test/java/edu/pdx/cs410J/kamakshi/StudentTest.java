package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * JUnit tests for the Student class.  These tests extend <code>InvokeMainTestCase</code>
 * which allows them to easily invoke the <code>main</code> method of <code>Student</code>.
 * They also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>matchers
 * for more readable assertion statements.
 */
public class StudentTest extends InvokeMainTestCase
{

    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Student.class, args);
    }

    private void assertErrorMessageExitCodeAndUsage(MainMethodResult result, String errorMessage) {
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getErr(), containsString(errorMessage));
        assertThat(result.getErr(), containsString(Student.USAGE_MESSAGE));
    }

    @Test
    public void noArgumentsPrintsMissingArgumentsToStandardError() {
        MainMethodResult result = invokeMain();
        String errorMessage = "Missing command line arguments";
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }

    @Test
    public void missingGenderPrintsMissingGenderToStandardError() {
        MainMethodResult result = invokeMain("Dave");
        String errorMessage = "Missing Gender";
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }

    @Test
    public void missingGpaPrintsMissingGpaToStandardError() {
        MainMethodResult result = invokeMain("Dave", "male");
        String errorMessage = Student.MISSING_GPA;
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }

    @Test
    public void missingClassesPrintsNothingToStandardErrorAndExitsZero() {
        MainMethodResult result = invokeMain("Dave", "male", "3.64");
        assertThat(result.getErr(), equalTo(""));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void noClassesPrintsDescriptionOfStudent() {
        MainMethodResult result = invokeMain("Dave", "male", "3.64");
        assertThat(result.getOut().trim(), equalTo("Dave has a GPA of 3.64 and is taking 0 classes.  " +
                "He says \"This class is too much work\"."));
    }

    @Test
    public void negativeGpaPrintsInvalidGpaToStandardError() {
        MainMethodResult result = invokeMain("Dave", "male", "-2");
        assertThat(result.getErr(), containsString("Invalid GPA: -2"));
    }

    @Test
    public void femaleStudentPrintsDescriptionToStandardOutput() {
        MainMethodResult result = invokeMain("Angela", "female", "4.0");
        assertThat(result.getOut().trim(), equalTo("Angela has a GPA of 4.0 and is taking 0 classes.  " +
                "She says \"This class is too much work\"."));
    }

    @Test
    public void uppercaseFemaleStudentPrintsDescriptionToStandardOutput() {
        MainMethodResult result = invokeMain("Angela", "Female", "4.0");
        assertThat(result.getOut().trim(), equalTo("Angela has a GPA of 4.0 and is taking 0 classes.  " +
                "She says \"This class is too much work\"."));
    }

    @Test
    public void uppercaseMaleStudentPrintsDescriptionToStandardOutput() {
        MainMethodResult result = invokeMain("David", "Male", "3.64");
        assertThat(result.getOut().trim(), equalTo("David has a GPA of 3.64 and is taking 0 classes.  " +
                "He says \"This class is too much work\"."));
    }

    @Test
    public void noClassesPrintsDescriptionOfStudentWithADifferentName() {
        MainMethodResult result = invokeMain("David", "male", "3.64");
        assertThat(result.getOut().trim(), equalTo("David has a GPA of 3.64 and is taking 0 classes.  " +
                "He says \"This class is too much work\"."));
    }

    @Test
    public void invalidGenderPrintsInvalidGenderToStandardError() {
        MainMethodResult result = invokeMain("David", "turtle", "3.64");
        assertThat(result.getErr(), containsString("Invalid gender: turtle"));
    }

    @Test
    public void oneClassPrintsNothingToStandardErrorAndExitsWithZero() {
        MainMethodResult result = invokeMain("David", "male", "3.64", "Advanced Java");
        assertThat(result.getErr(), equalTo(""));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void oneClassPrintDescriptionToStandardOutput() {
        MainMethodResult result = invokeMain("Dave", "male", "3.64", "Advanced Java");
        String expected = "Dave has a GPA of 3.64 and is taking 1 class: Advanced Java.  " +
                "He says \"This class is too much work\".";
        assertThat(result.getOut(), containsString(expected));
    }

    @Test
    public void exampleCommandLineFromAssignmentPrintsTheRightThing() {
        MainMethodResult result = invokeMain("Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
        String expected = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
                "Systems, and Java.  He says \"This class is too much work\".";
        assertThat(result.getOut(), containsString(expected));
    }

}
