package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the Project2 CS410J: Storing Your Phone Bill in a Text File.
 *
 * @author Kamakshi Nagar
 */
public class Project2 {

    static final String USAGE_MESSAGE = "args are (in this order)\n" +
            "  customer \n" +
            "  callerNumber : nnn-nnn-nnnn \n" +
            "  calleeNumber : nnn-nnn-nnnn \n" +
            "  startTime : MM/dd/yyyy HH:mm\n" +
            "  endTime : MM/dd/yyyy HH:mm\n"+
            "Options are (options may appear in this order)\n " +
            "-textFile filename :- should be a .txt file \n "+
            "-print \n" +
            "-README \n" ;


    /**
     * Main program that parses the command line arguments, creates a
     * <code>PhoneCall</code>, and prints a description of the call to
     * standard out by invoking PhoneCall <code>toString</code> method.
     * Also Write/Read to/from a file given as command line arguments for
     * command -textFile.
     *
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IllegalArgumentException {

        PhoneBill pb = new PhoneBill();
        PhoneCall pc = new PhoneCall();
        String fileName = null;
        boolean print= false; boolean write = false; boolean read = false;

        switch (args.length) {
            case 0:
                printErrorMessageAndExit("Missing command line arguments");
                break;

            case 1:
                if (args[0].equals("-README")) {
                    printReadmeMessage();
                } else if (args[0].equals("-print")) {
                    printErrorMessageAndExit("Missing Customer Name");
                } else if (args[0].equals("-textFile")) {
                    printErrorMessageAndExit("Missing FileName");
                } else
                    printErrorMessageAndExit("Missing Caller Number");
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    if (args[i].equals("-README")) printReadmeMessage();
                }
                if (args[0].equals("-print")) {
                    printErrorMessageAndExit("Missing Caller Number");
                } else if (args[0].equals("-textFile")) {
                    read = true;
                    fileName = validateFileName(args[1]);
                } else printErrorMessageAndExit("Missing Callee Number");
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    if (args[i].equals("-README")) printReadmeMessage();
                }
                if (args[0].equals("-print")) printErrorMessageAndExit("Missing Callee Number");
                if (args[0].equals("-textFile")) validateFileName(args[1]);
                else printErrorMessageAndExit("Missing StartDate");
                break;
            case 4:
                if (args[0].equals("-print")) {
                    printErrorMessageAndExit("Missing StartDate");
                }
                if (args[0].equals("-textFile")) {
                    printErrorMessageAndExit("Missing Callee Number");
                }
                printErrorMessageAndExit("Missing StartTime");
                break;
            case 5:
                if (args[0].equals("-print")) printErrorMessageAndExit("Missing StartTime");
                if (args[0].equals("-textFile")) {
                    printErrorMessageAndExit("Missing StartDate");
                }
                printErrorMessageAndExit("Missing EndDate");
                break;
            case 6:
                if (args[0].equals("-textFile")) {
                    printErrorMessageAndExit("Missing StartTime");
                }
                if (args[0].equals("-print")) printErrorMessageAndExit("Missing EndDate");
                printErrorMessageAndExit("Missing EndTime");
                break;
            case 7:
                if (args[0].equals("-textFile")) printErrorMessageAndExit("Missing EndDate");
                if (args[0].equals("-print")) printErrorMessageAndExit("Missing EndTime");
            case 8:
                if (args[0].equals("-textFile")) printErrorMessageAndExit("Missing EndTime");
           default:
               int i = 0;
            if(args[0].startsWith("-")){
                if (args[0].equals("-print")){
                    if (args.length>8){ printErrorMessageAndExit("Extraneous Argument");}
                    i = 1; print=true;}
                else if (args[0].equals("-textFile")){
                        if (args.length>9){ printErrorMessageAndExit("Extraneous Argument");}
                    {i=2; write = true; fileName=validateFileName(args[i-1]);}
                } else System.out.println("Invalid Command line Option");
            }else if (args.length>7){printErrorMessageAndExit("Extraneous Argument");}
            pb.customerName = args[i];
            pc.callerNumber = validatePhoneNumber(args[i + 1]);
            pc.calleeNumber = validatePhoneNumber(args[i + 2]);
            pc.startTimeString = validateTime(args[i + 3] + " " + args[i + 4]);
            pc.endTimeString = validateTime(args[i + 5] + " " + args[i + 6]);
        }

        if(print== true) {
            pb.addPhoneCall(pc);
            System.out.println(pc.toString());
        }
        if(write== true || read==true) { // Dump the family tree to the file
            try  {
                TextDumper dumper = new TextDumper(fileName);
                pb.addPhoneCall(pc);
                dumper.dump(pb);
                TextParser parser = new TextParser(fileName);
                pb = (PhoneBill)parser.parse();
                dumper.dump(pb);
            }catch (FileNotFoundException ex) {
                System.out.println("** Could not find file " + fileName);
            } catch (ParserException e) {
                System.err.println("Parser Exception: "+ e);
                e.printStackTrace();
            } catch (IOException ex) {System.err.println("** IOException while dealing with " + fileName);
            }
            System.exit(1);
        }
        /*if(read == true){
            try {
                TextParser parser = new TextParser(fileName);
                parser.parse();
            } catch (FileNotFoundException ex) {
                System.err.println("** Could not find file " + fileName);
            } catch (ParserException e) {
                System.err.println("Parser Exception: "+ e);
                e.printStackTrace();
            } catch (IOException ex){
                System.err.println("IO Exception : "+ ex);}
        System.exit(1);
        } */
    }

    /**
     * This Method Returns a validated fileName.
     * @param fileName A <code>String</code> name for a File.
     * @return same fileName which is validated.
     */
    private static String validateFileName(String fileName){
        if(fileName==null || !fileName.endsWith(".txt")){
            printErrorMessageAndExit("Invalid File Name");
        }
        return fileName;
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
