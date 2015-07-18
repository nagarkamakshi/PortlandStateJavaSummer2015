package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.InvokeMainTestCase;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by vaio on 09-07-2015.
 */
public class Project2Test extends InvokeMainTestCase {



    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    private void assertErrorMessageExitCodeAndUsage(MainMethodResult result, String errorMessage) {
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getErr(), containsString(errorMessage));
        assertThat(result.getErr(), containsString(Project2.USAGE_MESSAGE));
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertEquals(new Integer(1), result.getExitCode());
        assertThat(result.getErr(), containsString(Project2.USAGE_MESSAGE));
    }
    @Test
    public void testReadMeWorks(){
        MainMethodResult result = invokeMain("-README");
        assertEquals(new Integer(0), result.getExitCode());

    }
    @Test
    public void testPrintWorks(){
        MainMethodResult result = invokeMain("-print");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testBothPrintAndReadMeWorks(){
        MainMethodResult result = invokeMain("-print","-README");
        assertEquals(new Integer(0), result.getExitCode());
        //assertThat(result.getErr(),containsString("Missing name"));
    }
    @Test
    public void testNoArgumentAfterReadMeAndPrintWorks(){
        MainMethodResult result = invokeMain("-README","-print","Jams");
        assertEquals(new Integer(0), result.getExitCode());

    }
    @Test
    public void testArgumentWithLessArgumentsExitWithError(){
        MainMethodResult result = invokeMain("Jams");
        //assertEquals(new Integer(0), result.getExitCode());
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testNoArgumentAfterPrintExitWithErro(){
        MainMethodResult result = invokeMain("-print","Jams","jasdjka");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testAllArgumentsPrintNullString(){
        MainMethodResult result = invokeMain("Jams","dhakjs","hagsjha","sad");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testWithOneArgumentExitWithMissingCallerNumber(){
        MainMethodResult result = invokeMain("Jams");
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testBothReadMeAndPrintWork(){
        MainMethodResult result = invokeMain("-README","-print");
        String expected1 = "There is -textFile Command to read/write from/to a text file respectively.";
        //String expected2 = "Please enter a call first";
        assertThat(result.getOut(),containsString(expected1));
    }
    @Test
    public void testBothPrintAndReadMeWork(){
        MainMethodResult result = invokeMain("-print","-README");
        String expected1 ="There is -textFile Command to read/write from/to a text file respectively." ;
        assertThat(result.getOut(),containsString(expected1));
    }
    @Test
    public void testWithTwoArgumentExitWithMissingCalleeNumber(){
        MainMethodResult result = invokeMain("Jams","999-999-3333");
        assertEquals(new Integer(1), result.getExitCode());
        assertThat(result.getErr(), containsString("Missing Callee Number"));
    }
    @Test
    public void testOtherThanPrintArgumentAfterReadMeExitsWithError() {
        MainMethodResult result = invokeMain("-README","Jams");
        assertEquals(result.getExitCode(), new Integer(0));

    }
    @Test
    public void testMissingArgumentCallerNumberExitsWithError() {
        MainMethodResult result = invokeMain("-print","Jams");
        assertEquals(new Integer(1),result.getExitCode());
    }
    @Test
    public void testArgumentAfterReadMeAndPrintExitsWithError(){
        MainMethodResult result = invokeMain("-print","-README","Jams");
        assertEquals(result.getExitCode(), new Integer(0));
    }
    @Test
    public void testArgumentAfterPrintAndReadMeExitsWithError(){
        MainMethodResult result = invokeMain("-README","-print","Jams");
        assertEquals(result.getExitCode(),new Integer(0));

    }
    @Test
    public void testMissingArgumentCalleeNumberExitsWithError(){
        MainMethodResult result = invokeMain("-print","Jams","190-000-0000");
        assertEquals(new Integer(1),result.getExitCode());
    }
    @Test
    public void testOnlyTextFileCommandExitWithError(){
        MainMethodResult result = invokeMain("-textFile");
        //assertEquals(new Integer(1), result.getExitCode());
        assertErrorMessageExitCodeAndUsage(result, "Missing FileName");
    }
    @Test
    public void testTextFileCommandWithReadMe(){
        MainMethodResult result = invokeMain("-textFile","-README");
        //assertEquals(new Integer(1), result.getExitCode());
       assertThat(result.getOut(),containsString("This is a README for java Project2 : Storing a Phone Bill in a Text File at Portland State University Summer 2015, created by Kamakshi Nagar."));
    }
    @Test
    public void testTextFileArgumentsExitWithError(){
        MainMethodResult result = invokeMain("-textFile","-print","kam");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testInvalidCalleeNumberExitsWithError(){
        MainMethodResult result = invokeMain("kama","999-99-9999","000-00-00","1/15/2015","19:39","01/2/2015","1:02");
        assertThat(result.getErr(),containsString("Invalid number"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testBothStartTimeAndEndTimeWork(){
        MainMethodResult result = invokeMain("kama","999-999-9999","000-000-0000","1/15/2015","19:39","Jams","kama");
        assertThat(result.getErr(),containsString("Invalid Date Time"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testInvalidDateTimeExitsWithError(){
        MainMethodResult result = invokeMain("-print","Jams","999-999-9999","000-000-0000","kamak","19:39","01/02/2015","1:03");
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWhatHappensForArgumetSeries(){
        MainMethodResult result = invokeMain("Jams","999-999-9999","000-000-0000","11/15/2015","9:39","11/02/2015","01:03");
        assertThat(result.getOut(),containsString(""));
        //assertEquals(result.getExitCode(), new Integer(1));
    }

    @Test
    public void testInCorrectOutputForExtraArgument(){
        MainMethodResult result = invokeMain("Jams","999-999-9999","000-000-0000","11/15/2015","9:39","11/02/2015","01:03","kamakshi");
        assertThat(result.getErr(),containsString("Extraneous Argument"));
        //assertEquals(result.getExitCode(), new Integer(1));
    }

    @Test
    public void testPrintsInCorrectOutputForExtraArgument(){
        MainMethodResult result = invokeMain("-print","Jams","999-999-9999","000-000-0000","11/15/2015","9:39","11/02/2015","01:03","kamakshi");
        assertThat(result.getErr(),containsString("Extraneous Argument"));
        assertEquals(result.getExitCode(), new Integer(1));
    }
    @Test
    public void testAllPrintsInCorrectOutputForATextFile(){
        MainMethodResult result = invokeMain("-textFile","read.txt","Jams","999-999-9999","000-000-0000","11/15/2015","9:39","11/02/2015","01:03","kamakshi");
        assertThat(result.getErr(),containsString("Extraneous Argument"));
        assertEquals(result.getExitCode(), new Integer(1));
    }






}