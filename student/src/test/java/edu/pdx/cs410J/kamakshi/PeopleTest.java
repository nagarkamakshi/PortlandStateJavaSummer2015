package edu.pdx.cs410J.kamakshi;


import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by vaio on 19-06-2015.
 */
public class PeopleTest extends InvokeMainTestCase {
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(People.class, args);
    }
    private void assertErrorMessageExitCodeAndUsage(MainMethodResult result, String errorMessage) {
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getErr(), containsString(errorMessage));
        assertThat(result.getErr(), containsString(People.USAGE_MESSAGE));
    }

    @Test
    public void noArgumentsPrintsMissingArgumentsToStandardError() {
        MainMethodResult result = invokeMain();
        String errorMessage = "Missing command line arguments";
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }
    @Test
    public void twoArgumentsPrintsErrorAndExit() {
        MainMethodResult result = invokeMain("Dave", "male");
        String errorMessage = "Missing date";
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }
    @Test
    public void threeArgumentsPrintsErrorAndExit() {
        MainMethodResult result = invokeMain("Dave", "male","1/15/2015");
        String errorMessage = "Missing time";
        assertErrorMessageExitCodeAndUsage(result, errorMessage);
    }
    @Test
    public void exampleCommandLineFromAssignmentPrintsTheRightThing() {
        MainMethodResult result = invokeMain("Dave", "male","1/15/2015", "19:39");
        String expected = "This is Mr. Dave. He is so Handsome.Date is: 1/15/2015 19:39";
        assertThat(result.getOut(), containsString(expected));
    }
    @Test
    public void exampleCommandLineFromAssignmentPrintsTheRightThingWithTwoDigitsDate() {
        MainMethodResult result = invokeMain("Dave", "male","01/2/2015", "1:03");
        String expected = "This is Mr. Dave. He is so Handsome.Date is: 01/2/2015 1:03";
        assertThat(result.getOut(), containsString(expected));
    }


}
