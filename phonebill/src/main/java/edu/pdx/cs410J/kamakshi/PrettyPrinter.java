package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This class dumps a phone bill to a file in a text-based
 * format that is human readable and file will be pretty printed.
 *
 * @see TextParser, TextDumper
 * @author Kamakshi Nagar
 */
public class PrettyPrinter implements PhoneBillDumper {
    private PrintWriter pw;     // Where to pretty print to

    /**
     * Creates a new pretty printer that prints to a file of a given
     * name.  If the file does not exist, it is created.
     */
    public PrettyPrinter(String fileName) throws IOException {
        this(new File(fileName));
    }

    /**
     * Creates a new pretty printer that prints to a given file.
     */
    public PrettyPrinter(File file) throws IOException {
        this(new PrintWriter(new FileWriter(file), true));
    }

    /**
     * Creates a new pretty printer that prints to a
     * <code>PrintWriter</code>.  This way, we can print to destinations
     * other than files (such as the console).
     */
    PrettyPrinter(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * Prints the contents of the given Phone bill including its phone calls
     * in a human-readable format.
     *
     * @param abstractPhoneBill that need to be dumped on a file/destination
     * @throws IOException in writing to a file
     */

    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {


        SortedSet<PhoneCall> sortedTree = new TreeSet<>();
        sortedTree.addAll(abstractPhoneBill.getPhoneCalls());

        // Print a banner
        pw.println("Your Phone Bill");

        Iterator iter = sortedTree.iterator();

        while (iter.hasNext()) {
            PhoneCall call = (PhoneCall) iter.next();
            pw.println("Customer: "+ abstractPhoneBill.getCustomer());
            pw.println("Caller: " + call.getCaller() );
            pw.println("Callee: " + call.getCallee());
            pw.println("StartTime: "+ call.getStartTimeString());
            pw.println("EndTime: "+ call.getEndTimeString());
            long l= call.getEndTime().getTime()- call.getStartTime().getTime();
            pw.println("Call duration: "+ TimeUnit.MILLISECONDS.toMinutes(l) + " Minutes");
            pw.println("\n");
        }
        //Flush and Close the print writer
        pw.flush();
        pw.close();


    }
}
