package edu.pdx.cs410J.kamakshi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J: Designing a Phone Bill Application Project.
 *
 * @author Kamakshi Nagar
 */
public class Project1 {

  static final String USAGE_MESSAGE = "args are (in this order)\n" +
          "  customer \n" +
          "  callerNumber : nnn-nnn-nnnn \n" +
          "  calleeNumber : nnn-nnn-nnnn \n" +
          "  startTime : MM/dd/yyyy HH:mm\n" +
          "  endTime : MM/dd/yyyy HH:mm\n"+
          "Options are (options may appear in this order)\n " +
          "-print \n"
          +"-README \n" ;

    /**
     * Main program that parses the command line arguments, creates a
     * <code>PhoneCall</code>, and prints a description of the call to
     * standard out by invoking PhoneCall <code>toString</code> method.
     *
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IllegalArgumentException{

      PhoneBill pb = new PhoneBill();
      PhoneCall pc = new PhoneCall();

      switch (args.length) {
          case 0:
              printErrorMessageAndExit("Missing command line arguments");
              break;

          case 1:
              if (args[0].equals("-README")) {
                  printReadmeMessage();
              }else if (args[0].equals("-print")){
                  toStringMethodMessage(pc.toString());
              }else    printErrorMessageAndExit("Missing Caller Number");
              break;

          case 2:
              boolean b = ((args[0].equals("-README") || args[0].equals("-print"))&& (args[1].equals("-README") || args[1].equals("-print")));
              if(b){
                  printReadmeMessage();
                  toStringMethodMessage(pc.toString());
              }else if((args[0].equals("-README") || args[0].equals("-print")) && (!args[1].equals("-README")||!args[1].equals("-print"))) {
                  if(args[0].equals("-print")&& !args[1].equals("-README")){
                      printErrorMessageAndExit("Missing Caller Number");
                  }
                  System.err.println("Only print/Readme option is allowed");
                  System.exit(1);
              }
              else printErrorMessageAndExit("Missing Callee Number");
              break;
          case 3:
              if ((args[0].equals("-README") && args[1].equals("-print"))||(args[1].equals("-README") && args[0].equals("-print"))) {
                  System.err.println("No arguments after print/Readme option is allowed");
                  System.exit(0);
              }if (args[0].equals("-print")){
                  printErrorMessageAndExit("Missing Callee Number");
              }else printErrorMessageAndExit("Missing StartDate");
              break;
          case 4:
              if (args[0].equals("-print")){
                  printErrorMessageAndExit("Missing StartDate");
              }
              printErrorMessageAndExit("Missing StartTime");
              break;
          case 5:
              if (args[0].equals("-print")){
                  printErrorMessageAndExit("Missing StartTime");
              }
              printErrorMessageAndExit("Missing EndDate");
              break;
          case 6:
              if (args[0].equals("-print")){
                  printErrorMessageAndExit("Missing EndDate");
              }
              printErrorMessageAndExit("Missing EndTime");
              break;
          default:

              int i =0;
              if(args[0].equals("-print")){ i=1; }
              pb.customerName = args[i];
              pc.callerNumber = validatePhoneNumber(args[i+1]);
              pc.calleeNumber = validatePhoneNumber(args[i+2]);
              pc.startTimeString = validateTime(args[i+3]+" "+args[i+4]);
              pc.endTimeString = validateTime(args[i+5]+" "+args[i+6]);
      }
        pb.addPhoneCall(pc);
        System.out.println(pc.toString());

  }

    /**
     * This Method has description message for -README command line Argument.
     * It describes the Project1 and the classes we will be using for it.
     */
    private static void printReadmeMessage() {
        System.out.print("This is a README for java Project1 : Designing a Phone Bill Application at Portland State University Summer 2015, created by Kamakshi Nagar.\n" +
                "The project is to generate a phone bill for the customers by maintaining the record of calls at given time. We enter customer name, phone number and callee's phone number for a given time, we record the call, which can be printed using the -print command.\n" +
                "We have three java files, Project1.java, PhoneBill.java, PhoneCall.java. PhoneBill has Customer name for whom we are generating the Bill and PhoneCall has the callerNumber, calleeNumber, startTime and endTime of call. Project1.java is the main file in which command line parsing is done, it has coding for both README ans print commands as well as methods to validate phone numbers and date-time as they have specific format.");
        System.exit(0);
    }

    /**
     * Returns the validated <code>String</code>
     * @param cNumber The <code>String</code> to be validated for the pattern(nnn-nnn-nnnnn).
     * @return validated <code>String</code> cNumber
     */
    private static String validatePhoneNumber(String cNumber) {

        String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
        if (!cNumber.matches(phoneNumberPattern)){
            printErrorMessageAndExit("Invalid number");}
        return cNumber;
    }

    /**
     * Returns the validated <code>String</code>
     *
     * @param dateTime The <code>String</code> that has to be validated for format (MM/dd/yyyy HH:mm).
     * @return validated <code>String</code> dateTime
     *
     */
    private static String validateTime(String dateTime) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        dateFormat.setLenient(false);
        try {
            Date newDate = dateFormat.parse(dateTime);
            dateTime = newDate.toString();
        } catch (ParseException e) {
            printErrorMessageAndExit("Invalid Date Time");
            e.printStackTrace();}
        return dateTime;
    }

    /**
     * This method checks if -print is given with the call details, If not, it prints the custom message to standard out
     * @param Message The <code>String</code> from PhoneCall <code>toString()</code> method.
     */
    private static void toStringMethodMessage(String Message) {
        if(Message.contains("null")){
            System.out.println("Please enter a call first \n");
        }else{
            System.out.println(Message);
        }
        System.exit(0);
    }

    /**
     * This method prints the error message along with usage message for invalid command line arguments
     * The program terminates after the execution of this method.
     * @param errorMessage The error message for specific argument.
     */
    private static void printErrorMessageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println(USAGE_MESSAGE);
        System.exit(1);
    }


}
