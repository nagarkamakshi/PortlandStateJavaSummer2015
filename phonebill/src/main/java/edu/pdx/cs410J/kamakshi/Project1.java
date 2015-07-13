package edu.pdx.cs410J.kamakshi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  static final String USAGE_MESSAGE = "args are (in this order)\n" +
          "  customer \n" +
          "  callerNumber : nnn-nnn-nnnn \n" +
          "  calleeNumber : nnn-nnn-nnnn \n" +
          "  startTime : mm/dd/yyyy hh:mm\n" +
          "  endTime : mm/dd/yyyy hh:mm\n"+
          "Options are (options may appear in this order)\n " +
          "-print \n"
          +"-README \n" ;

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
                  System.exit(0); }
              else if (args[0].equals("-print")){
                  toStringMethodMessage(pc.toString());
                  System.exit(0);
              }else    printErrorMessageAndExit("Missing Caller Number");
              break;

          case 2:
              boolean b = ((args[0].equals("-README") || args[0].equals("-print"))&& (args[1].equals("-README") || args[1].equals("-print")));
              if(b){
                  printReadmeMessage();
                  toStringMethodMessage(pc.toString());
                  System.exit(0);
              }
              else if((args[0].equals("-README") || args[0].equals("-print")) && (!args[1].equals("-README")||!args[1].equals("-print"))) {
                  if(args[0].equals("-print")&& !args[1].equals("-README")){
                      printErrorMessageAndExit("Missing Caller Number");
                  }
                  System.err.println("Only print/Readme option is allowed");
                  System.exit(0);
              }
              else printErrorMessageAndExit("Missing Callee Number");
              break;
          case 3:
              if ((args[0].equals("-README") && args[1].equals("-print"))||(args[1].equals("-README") && args[0].equals("-print"))) {
                  System.err.println("No arguments after print/Readme option is allowed");
                  System.exit(0);
              }
              if (args[0].equals("-print")){
                  printErrorMessageAndExit("Missing Callee Number");
              }
              else printErrorMessageAndExit("Missing StartDate");
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
      System.out.println(pc.toString());
  }

    private static void toStringMethodMessage(String Message) {
       if(Message.contains("null")){
            System.out.println("Please enter a call first \n");
        }else{
           System.out.println(Message);
        }
    }

    private static void printReadmeMessage() {
        System.out.print("This is a README file for java Project at Portland State University Summer 2015.\n" +
                "The project is to design an application to generate a phone bill application for the customers.\n");

    }
    private static String validatePhoneNumber(String cNumber) {

        String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
        if (!cNumber.matches(phoneNumberPattern)){
            printErrorMessageAndExit("Invalid number");}
        return cNumber;
    }
    private static String validateTime(String dateTime) {

        Date newDate= null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm");
        //dateFormat.setLenient(false);
        try {
            newDate = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            printErrorMessageAndExit("Invalid Date Time");
            e.printStackTrace();}
        return newDate.toString();
    }

    private static void printErrorMessageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println(USAGE_MESSAGE);
        System.exit(1);
    }


}