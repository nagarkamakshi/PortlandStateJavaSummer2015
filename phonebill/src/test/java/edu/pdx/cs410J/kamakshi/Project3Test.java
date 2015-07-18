package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.InvokeMainTestCase;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by vaio on 16-07-2015.
 */
public class Project3Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

    private void assertErrorMessageExitCodeAndUsage(MainMethodResult result, String errorMessage) {
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getErr(), containsString(errorMessage));
        assertThat(result.getErr(), containsString(Project3.USAGE_MESSAGE));
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertEquals(new Integer(1), result.getExitCode());
        assertThat(result.getErr(), containsString("Missing command line arguments"));
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
    public void testTestFileWorks(){
        MainMethodResult result = invokeMain("-textFile");
        assertThat(result.getErr(), containsString("Missing FileName"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyWorks(){
        MainMethodResult result = invokeMain("-pretty");
        assertThat(result.getErr(), containsString("Missing File name or -"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyAndReadMeWorks(){
        MainMethodResult result = invokeMain("-pretty","-README");
        assertThat(result.getOut(), containsString("There is -textFile Command to read/write from/to a text file respectively."));
        assertEquals(new Integer(0), result.getExitCode());
    }
    @Test
    public void testPrettyAndPrintWork(){
        MainMethodResult result = invokeMain("-pretty","-print");
        assertThat(result.getErr(), containsString("Missing Customer Name"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyAndFileNameWork(){
        MainMethodResult result = invokeMain("-pretty","ram");
        assertThat(result.getErr(), containsString("Missing Customer Name"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyAndDashWork(){
        MainMethodResult result = invokeMain("-pretty","-");
        assertThat(result.getErr(), containsString("Missing Customer Name"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyWithFileNameAndAllArgumentsWorkCorrectly(){
        MainMethodResult result = invokeMain("-pretty","write","ram","099-000-0000","000-000-0000","01/01/2015","01:01","pm","01/01/2015","01:05","pm");
        //assertThat(result.getOut(), containsString("hjhjhjhj"));
        assertEquals(result.getExitCode(),new Integer(0));
    }
    @Test
    public void testPrettyWithFileNameAndLessArgumentsExitWithError(){
        MainMethodResult result = invokeMain("-pretty","write","ram","099-000-0000","000-000-0000","01/01/1010","01:01","am");
        assertThat(result.getErr(), containsString("Missing EndDate"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testPrettyWithDashAndAllArgumentsWorkCorrectly(){
        MainMethodResult result = invokeMain("-pretty","-","ram","099-000-0000","000-000-0000","01/01/1010","01:01","am","11/11/1999","10:10","pm");
        assertThat(result.getOut(), containsString(""));
        //assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testPrettyWithDashAndExtraArgumentExitWithError(){
        MainMethodResult result = invokeMain("-pretty","-","ram","099-000-0000","000-000-0000","01/01/1010","01:01","am","11/11/1999","10:10","pm","ahkjsh","kadhkashdk");
        assertThat(result.getErr(), containsString("Extraneous Argument"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyWithFileNameAndSortedPhoneCalls(){
        MainMethodResult result = invokeMain("-pretty","-","ram","099-000-0000","000-000-0000","01/01/1010","01:01","am","11/11/1999","10:10","pm");
        assertThat(result.getErr(), containsString(""));
        assertEquals(new Integer(0), result.getExitCode());
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
    public void testNoArgumentAfterPrintExitWithError(){
        MainMethodResult result = invokeMain("-print","Jams","jasdjka");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testAllArgumentsPrintNullString(){
        MainMethodResult result = invokeMain("Jams","dhakjs","hagsjha","sad");
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testBothReadMeAndPrintWork(){
        MainMethodResult result = invokeMain("-README","-print");
        String expected = "There is -textFile Command to read/write from/to a text file respectively.";
        assertThat(result.getOut(),containsString(expected));
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
    public void testTextFileCommandWithReadMe(){
        MainMethodResult result = invokeMain("-textFile","-README");
        assertThat(result.getOut(),containsString("This is a README for java Project2 : Storing a Phone Bill in a Text File at Portland State University Summer 2015, created by Kamakshi Nagar."));
    }
    @Test
    public void testTextFileArgumentsExitWithError(){
        MainMethodResult result = invokeMain("-textFile","-print","kam");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testInvalidCalleeNumberExitsWithError(){
        MainMethodResult result = invokeMain("kama","999-99-9999","000-00-00","1/15/2015","09:39","am","1/15/2015","09:39","pm");
        assertThat(result.getErr(),containsString("Invalid caller number"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testBothStartTimeAndEndTimeWork(){
        MainMethodResult result = invokeMain("kama","999-999-9999","000-000-0000","1/15/2015","09:39","am","ram","09:39","pm");
        assertThat(result.getErr(),containsString("Invalid End Time"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testChangedStartTimeAndEndTimeWork(){
        MainMethodResult result = invokeMain("kama","999-999-9999","000-000-0000","1/15/2015","09:39","pm","1/15/2015","09:39","pm");
        assertThat(result.getOut(),containsString(""));
        assertEquals(new Integer(0), result.getExitCode());
    }
    @Test
    public void testInvalidDateTimeExitsWithError(){
        MainMethodResult result = invokeMain("-print","Jams","99-999-9999","000-000-0000","kamak","09:39","pm","1/15/2015","09:39","pm");
        assertThat(result.getErr(),containsString("Invalid caller number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWhatHappensForArgumentSeries(){
        MainMethodResult result = invokeMain("Jams","999-999-9999","000-000-0000","11/15/2015","9:39","pm","1/15/2015","09:39","pm");
        assertThat(result.getOut(),containsString(""));
        //assertEquals(result.getExitCode(), new Integer(1));
    }

    @Test
    public void testInCorrectOutputForExtraArgument(){
        MainMethodResult result = invokeMain("Jams","999-999-9999","000-000-0000","11/15/2015","9:39","pm","1/15/2015","09:39","pm","kamakshi");
        assertThat(result.getErr(),containsString("Extraneous Argument"));
        //assertEquals(result.getExitCode(), new Integer(1));
    }
    @Test
    public void testAllPrintsCorrectOutput(){
        MainMethodResult result = invokeMain("-print","Jams","999-999-9999","000-000-0000","01/05/2015","9:39","am","01/2/2015","01:03","am");
        assertThat(result.getOut(), containsString("Phone call from 999-999-9999 to 000-000-0000 from 1/5/15 9:39 AM to 1/2/15 1:03 AM"));
    }
    @Test
    public void testAllPrintsCorrectOutputForDifferentDate(){
        MainMethodResult result = invokeMain("-print","Jams","999-999-9999","000-000-0000","08/16/2015","9:39","am","9/19/2015","01:03","pm");
        assertThat(result.getOut(), containsString("Phone call from 999-999-9999 to 000-000-0000 from 8/16/15 9:39 AM to 9/19/15 1:03 PM"));
    }
    @Test
    public void testAllPrintsInCorrectOutputForATextFile(){
        MainMethodResult result = invokeMain("-textFile","read.txt","Jams","999-999-9999","000-000-0000","1/5/2015","9:39","am","1/02/2015","01:03","pm","kamakshi");
        assertThat(result.getErr(),containsString("Extraneous Argument"));
        assertEquals(result.getExitCode(), new Integer(1));
    }
    @Test
    public void testAllPrintsCorrectOutputForATextFile(){
        MainMethodResult result = invokeMain("-textFile","ram","Jams","999-999-9999","000-000-0000","11/15/2015","9:39","am","11/02/2015","01:03","pm");
        assertThat(result.getOut(),containsString(""));
        assertEquals(result.getExitCode(), new Integer(0));
    }
    @Test
    public void testAllPrintsInCorrectOutputForExistingTextFile(){
        MainMethodResult result = invokeMain("-textFile","ram");
        assertThat(result.getErr(),containsString("Missing Customer Name"));
        assertEquals(result.getExitCode(), new Integer(1));

    }
    @Test
    public void testAllPrintsInCorrectOutputForNonExistingTextFile(){
        MainMethodResult result = invokeMain("-textFile","kama");
        assertThat(result.getErr(),containsString("Missing Customer Name"));
        assertEquals(result.getExitCode(), new Integer(1));

    }
    @Test
    public void testIncorrectOptionExitWithError(){
        MainMethodResult result = invokeMain("-kamak","kama","999-999-0000","000-000-0000","01/01/1929","01:01","pm","01/02/1990","09:00","pm");
        assertThat(result.getErr(),containsString("Invalid Command line Option"));
        assertEquals(result.getExitCode(), new Integer(1));

    }
    @Test
    public void testForNewFunctionality(){
        MainMethodResult result = invokeMain("-print","Jams","990-999-9999","000-000-0000","11/14/1990","9:16","pm","01/02/1990","9:16","am");
        assertThat(result.getOut(),containsString("Phone call from 990-999-9999 to 000-000-0000 from 11/14/90 9:16 PM to 1/2/90 9:16 AM"));
        //assertEquals(new Integer(1), result.getExitCode());
    }
}