package edu.pdx.cs410J.kamakshi;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import edu.pdx.cs410J.InvokeMainTestCase;
import static junit.framework.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

    private void assertErrorMessageExitCodeAndUsage(MainMethodResult result, String errorMessage) {
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getErr(), containsString(errorMessage));
        assertThat(result.getErr(), containsString(Project1.USAGE_MESSAGE));
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
    public void testOnlyReadMeOptionWorks() {
        MainMethodResult result = invokeMain("-README");
        String expected = "This is a README file for java Project at Portland State University Summer 2015.\n" +
                "The project is to design an application to generate a phone bill application for the customers.\n";
        assertThat(result.getOut(),containsString(expected));
    }
    @Test
    public void testOnlyPrintOptionWorks(){
        MainMethodResult result = invokeMain("-print");
        String expected = "Please enter a call first";
        assertThat(result.getOut(),containsString(expected));
    }
    @Test
    public void testWithOneArgumentExitWithMissingCallerNumber(){
      MainMethodResult result = invokeMain("Jams");
      assertEquals(new Integer(1), result.getExitCode());
      assertThat(result.getErr(), containsString("Missing Caller Number"));
    }

    @Test
    public void testBothReadMeAndPrintWork(){
        MainMethodResult result = invokeMain("-README","-print");
        String expected1 = "This is a README file for java Project at Portland State University Summer 2015.\n" +
                "The project is to design an application to generate a phone bill application for the customers.\n";
        String expected2 = "Please enter a call first";
        assertThat(result.getOut(),containsString(expected1+expected2));
    }
    @Test
    public void testBothPrintAndReadMeWork(){
        MainMethodResult result = invokeMain("-print","-README");
        String expected1 = "This is a README file for java Project at Portland State University Summer 2015.\n" +
                "The project is to design an application to generate a phone bill application for the customers.\n";
        String expected2 = "Please enter a call first";
        assertThat(result.getOut(),containsString(expected1+expected2));
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
        assertThat(result.getErr(),containsString("Only print/Readme option is allowed"));
        assertEquals(new Integer(0),result.getExitCode());
    }
    @Test
    public void testMissingArgumentCallerNumberExitsWithError() {
        MainMethodResult result = invokeMain("-print","Jams");
        assertThat(result.getErr(),containsString("Missing Caller Number"));
        assertEquals(new Integer(1),result.getExitCode());
    }
    @Test
    public void testArgumentAfterReadMeAndPrintExitsWithError(){
        MainMethodResult result = invokeMain("-print","-README","Jams");
        assertThat(result.getErr(),containsString("No arguments after print/Readme option is allowed"));
        assertEquals(new Integer(0),result.getExitCode());
    }
    @Test
    public void testArgumentAfterPrintAndReadMeExitsWithError(){
        MainMethodResult result = invokeMain("-README","-print","Jams");
        assertThat(result.getErr(),containsString("No arguments after print/Readme option is allowed"));
        assertEquals(new Integer(0),result.getExitCode());
    }
    @Test
    public void testMissingArgumentCalleeNumberExitsWithError(){
        MainMethodResult result = invokeMain("-print","Jams","190-000-0000");
        assertThat(result.getErr(),containsString("Missing Callee Number"));
        assertEquals(new Integer(1),result.getExitCode());
    }
    @Test
    public void testInvalidCallerNumberExitsWithError(){
        MainMethodResult result = invokeMain("kama","9","000-00-0000","1/15/2015","19:39","01/2/2015","19:90");
        assertThat(result.getErr(), containsString("Invalid number"));
    }
    @Test
    public void testInvalidCalleeNumberExitsWithError(){
        MainMethodResult result = invokeMain("kama","999-99-9999","000-00-00","1/15/2015","19:39","01/2/2015","1:02");
        assertThat(result.getErr(), containsString("Invalid number"));
    }
    @Test
    public void testBothStartTimeAndEndTimeWork(){
        MainMethodResult result = invokeMain("kama","999-999-9999","000-000-0000","1/15/2015","19:39","Jams","kama");
        assertThat(result.getErr(), containsString("Invalid Date Time"));
    }
    @Test
    public void testAllPrintsInCorrectOutput(){
        MainMethodResult result = invokeMain("-print","Jams","999-999-9999","000-000-0000","1/15/2015","19:39","01/2/2015","1:03");
        assertThat(result.getOut(), containsString("Phone call from 999-999-9999 to 000-000-0000 from Thu Jan 15 19:39:00 PST 2015 to Fri Jan 02 01:03:00 PST 2015"));
    }






}