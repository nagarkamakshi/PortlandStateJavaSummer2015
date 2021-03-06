package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import java.io.*;

/**
 * The main class for the Project3 CS410J: Pretty Printing a Phone Bill.
 *
 * @author Kamakshi Nagar
 */
public class Project3 {

    static final String USAGE_MESSAGE = "args are (in this order)\n" +
            "  customer \n" +
            "  callerNumber : nnn-nnn-nnnn \n" +
            "  calleeNumber : nnn-nnn-nnnn \n" +
            "  startTime : MM/dd/yyyy hh:mm AM/PM\n" +
            "  endTime : MM/dd/yyyy hh:mm AM/PM\n"+
            "Options are (options may appear in this order)\n " +
            "-pretty file \n"+
            "-textFile filename \n "+
            "-print \n" +
            "-README \n" ;
    private static boolean pretty= false;
    private static String fileName = null;
    private static boolean print= false;
    private static boolean write = false;
    private static String customerName= null;

    private static PhoneBill pb = new PhoneBill();
    private static PhoneCall pc = new PhoneCall();

    /**
     * Main program that parses the command line arguments, creates a
     * <code>PhoneCall</code>, and prints a description of the call to
     * standard out by invoking PhoneCall <code>toString</code> method.
     * Also Write/Read to/from a file given as command line arguments for
     * command -textFile.
     * A -pretty Command for pretty printing the PhoneCall from the file.
     *
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IllegalArgumentException{

        switch (args.length) {
            case 0:
                printErrorMessageAndExit("Missing command line arguments");
                break;

            case 1:
                switch (args[0]) {
                    case "-README":printReadmeMessage();
                    case "-print":printErrorMessageAndExit("Missing Customer Name");
                    case "-textFile":printErrorMessageAndExit("Missing FileName");
                    case "-pretty":printErrorMessageAndExit("Missing File name or -");
                    default:printErrorMessageAndExit("Missing Caller Number");
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    if (args[i].equals("-README")) printReadmeMessage();
                }
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing Caller Number");
                    case "-textFile":printErrorMessageAndExit("Missing Customer Name");
                    case "-pretty":printErrorMessageAndExit("Missing Customer Name");
                    default:printErrorMessageAndExit("Missing Callee Number");
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    if (args[i].equals("-README")) printReadmeMessage();
                }
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing Callee Number");
                    case "-textFile":printErrorMessageAndExit("Missing Caller Number");
                    case "-pretty":printErrorMessageAndExit("Missing Caller Number");
                    default:printErrorMessageAndExit("Missing StartDate");
                }
                break;
            case 4:
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing StartDate");
                    case "-textFile":printErrorMessageAndExit("Missing Callee Number");
                    case "-pretty":printErrorMessageAndExit("Missing Callee Number");
                    default: printErrorMessageAndExit("Missing StartTime");
                }
                break;
            case 5:
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing StartTime");
                    case "-textFile":printErrorMessageAndExit("Missing StartDate");
                    case "-pretty":printErrorMessageAndExit("Missing StartDate");
                    default:printErrorMessageAndExit("Missing AM/PM");
                }
                break;
            case 6:
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing AM/PM");
                    case "-textFile":printErrorMessageAndExit("Missing StartTime");
                    case "-pretty":printErrorMessageAndExit("Missing StartTime");
                    default:printErrorMessageAndExit("Missing EndDate");
                }
                break;
            case 7:
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing EndDate");
                    case "-textFile":printErrorMessageAndExit("Missing AM/PM");
                    case "-pretty":printErrorMessageAndExit("Missing AM/PM");
                    default:printErrorMessageAndExit("Missing EndTime");
                }
                break;
            case 8:
                switch (args[0]) {
                    case "-print":printErrorMessageAndExit("Missing EndTime");
                    case "-textFile":printErrorMessageAndExit("Missing EndDate");
                    case "-pretty":printErrorMessageAndExit("Missing EndDate");
                    default:printErrorMessageAndExit("Missing AM/PM");
                }
                break;
            case 9:
                switch (args[0]) {
                    case "-print": printErrorMessageAndExit("Missing AM/PM");
                    case "-textFile":printErrorMessageAndExit("Missing EndTime");
                    case "-pretty":printErrorMessageAndExit("Missing EndTime");
                }
            case 10:
                switch (args[0]) {
                    case "-textFile":printErrorMessageAndExit("Missing AM/PM");
                    case "-pretty":printErrorMessageAndExit("Missing AM/PM");
                }
            default:
                int i = 0;
                if(args[0].startsWith("-")){
                    if (args.length > 12) {printErrorMessageAndExit("Extraneous Argument");
                    }
                    switch (args[0]) {
                        case "-print":
                            i = 1;print = true;
                            if(args[1].equals("-pretty")){i=3; pretty= true;fileName = args[i - 1];}
                            break;
                        case "-textFile":
                            i = 2; write = true;fileName = args[i - 1];
                            if(args[2].equals("-print")){i=3; print= true;}
                            break;
                        case "-pretty":
                            i = 2;pretty = true;fileName = args[i-1];
                            if(args[2].equals("-print")){i=3; print= true;}
                            break;
                        default:
                            printErrorMessageAndExit("Invalid Command line Option");
                            break;
                    }
                }else if (args.length>9){printErrorMessageAndExit("Extraneous Argument");}

                pb = new PhoneBill(args[i]);
                customerName = pb.customerName;
                pc = new PhoneCall(args[i+1],args[i+2],args[i+3]+" "+ args[i+4]+" "+args[i+5],args[i+6]+" "+ args[i+7]+" "+args[i+8]);
        }

        if(print) {printPhoneCall();}
        if(write) { // Dump the phoneBill to the file
            File file= new File(fileName);
            if(file.exists()) readWriteFromOrToTextFile();
            else {
                try {
                    pb.addPhoneCall(pc);
                    TextDumper dumper = new TextDumper(file);
                    dumper.dump(pb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        } else if (pretty){
            prettyPrintingToAFile();
            System.exit(0);
        }
        else pb.addPhoneCall(pc); System.exit(0);
    }
    /**
     * This Method has description message for -README command line Argument.
     * It describes the Project1 and the classes we will be using for it.
     */
    private static void printReadmeMessage() {
        System.out.print("This is a README for java Project3 : Pretty Printing a Phone Bill in a Text File at Portland State University Summer 2015, created by Kamakshi Nagar.\n" +
                "The project is to generate a phone bill for the customers by maintaining the record of calls at given time. We enter customer name, phone number and callee's phone number for a given time, we record the call, which can be printed using the -print command.\n" +
                "There is -textFile Command to read/write from/to a text file respectively.\n" +
                "A -pretty command can be used to pretty print the phone bill in a file.\n"+
                "We have java files as Project3.java,PrettyPrinter.java, PhoneBill.java, PhoneCall.java, TextParser.java, TextDumper.java.\n" +
                "PhoneBill has Customer name for whom we are generating the Bill and PhoneCall has the callerNumber, calleeNumber, startTime and endTime of call.\n" +
                "Project3.java is the main file in which command line parsing is done, it has coding for README, print,textFile and pretty commands.\n");
        System.exit(0);
    }

    /**
     *This Method add call to Phone Bill and print a phone call to Standard Out.
     *
     */
    private static void printPhoneCall(){
        pb.addPhoneCall(pc);
        System.out.println(pc.toString());
    }

    /**
     *This Method Parse the text in a textFile a pretty print the Phone Bill
     * including its calls to a file.
     * Reading and Writing can throw Parse, File and IO exceptions.
     */

    private static void readWriteFromOrToTextFile(){
        try  {
                TextParser parser= new TextParser(fileName);
                AbstractPhoneBill bill = parser.parse();
                pb=(PhoneBill)bill;
                String name= pb.customerName;
                pb.addPhoneCall(pc);
                if (!customerName.equals(name)){printErrorMessageAndExit("Name Mismatch");}
                PrettyPrinter pretty = new PrettyPrinter(fileName);
                pretty.dump(pb);

        }catch (FileNotFoundException ex) {
            System.err.println("** Could not find file " + fileName);
        } catch (ParserException e) {
            System.err.println("Parser Exception: "+ e);
            e.printStackTrace();
        } catch (IOException ex) {
            System.err.println("** IOException while dealing with " + fileName);
        }
        System.exit(0);
    }

    /**
     * This Method adds the phone call to the phone Bill and
     * writes it to either Console or a file in a pretty format.
     */
    private static void prettyPrintingToAFile(){
        try {
             pb.addPhoneCall(pc);
            PrettyPrinter pretty;
            if(fileName.equals("-")){
                PrintWriter out = new PrintWriter(System.out,true);
                pretty = new PrettyPrinter(out);
            }
            else {File file = new File(fileName);
            if(file.exists()){
                TextParser parser= new TextParser(fileName);
                AbstractPhoneBill bill = parser.parse();
                pb=(PhoneBill)bill;
                String name= pb.customerName;
                pb.addPhoneCall(pc);
                if (!customerName.equals(name)){printErrorMessageAndExit("Name Mismatch");}
                pretty = new PrettyPrinter(fileName);
                pretty.dump(pb);
            }else{
                pretty = new PrettyPrinter(file);} }

            pretty.dump(pb);

        } catch (FileNotFoundException ex) {
            System.err.println("** Could not find file " + fileName);
        }catch(ParserException e){
            System.err.println("Parser Error "+ e.getMessage());
        }
        catch (IOException e) {
            System.err.println("** " + e.getMessage());
            e.printStackTrace();
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
