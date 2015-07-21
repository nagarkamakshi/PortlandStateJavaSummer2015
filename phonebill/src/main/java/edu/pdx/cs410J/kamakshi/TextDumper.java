package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
* This class dumps a phone bill to a file in a text-based
 * format that is meant to be parsed by a TextParser.
 *
 * @see TextParser, PrettyPrinter
 * @author Kamakshi Nagar
 */
public class TextDumper implements PhoneBillDumper {
    private PrintWriter pw;      // Dumping destination

    /**
     * Creates a new text dumper that dumps to a file of a given name.
     * If the file does not exist, it is created.
     */
    public TextDumper(String fileName) throws IOException {
        this(new File(fileName));
    }

    /**
     * Creates a new text dumper that dumps to a given file.
     */
    public TextDumper(File file) throws IOException {
        this(new PrintWriter(new FileWriter(file), true));
    }
    /**
     * Creates a new text dumper that prints to a
     * <code>PrintWriter</code>.  This way, we can dump to destinations
     * other than files.
     */
    public TextDumper(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * Dumps the contents of a PhoneBill to the TextFile.
     */
    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {

        Iterator iter = abstractPhoneBill.getPhoneCalls().iterator();
        while (iter.hasNext()) {
            PhoneCall phonecall = (PhoneCall) iter.next();

            int lines = 0;   // How many lines in the encoding
            StringBuffer data = new StringBuffer();
            data.append("Caller: " + phonecall.getCaller() + "\n");
            lines++;
            data.append("Callee: " + phonecall.getCallee() + "\n");
            lines++;
            data.append("StartTime: " + phonecall.getStartTimeString() + "\n");
            lines++;
            data.append("EndTime: " + phonecall.getEndTimeString() + "\n");
            lines++;
            // Write the header followed by the data to the destination
            pw.println("Customer: " +abstractPhoneBill.getCustomer());
            pw.print(data.toString());
        }

        //Flush and Close the print writer
        pw.flush();
        pw.close();

    }
}
