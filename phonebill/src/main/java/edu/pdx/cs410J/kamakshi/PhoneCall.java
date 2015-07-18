package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.ParserException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Comparable;
import java.util.Locale;

/**
 * This Class inherits its super class AbstractPhoneCall And its methods.
 * It has call details which will be added to the customer's PhoneBill.
 *
 *
 * @see PhoneBill
 * @author Kamakshi Nagar
 */
public class PhoneCall  extends AbstractPhoneCall implements Comparable<PhoneCall>{

    /**
     * The <code>String</code>  are null.
     */
    String callerNumber = null;
    String calleeNumber = null;
    String startTimeString= null;
    String endTimeString= null;

    public PhoneCall() {
    }

    public PhoneCall(String callerNumber, String calleeNumber, String startTimeString, String endTimeString) {
        this.calleeNumber = calleeNumber;
        this.callerNumber = callerNumber;
        this.startTimeString = startTimeString;
        this.endTimeString = endTimeString;
    }

    /**
     * Returns the <code>String</code> callerNumber of current PhoneCall Object.
     *
     * @return <code>String</code> callerNumber
     */
    @Override
    public String getCaller() {
        String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
        if (!this.callerNumber.matches(phoneNumberPattern)){
            System.err.println("Invalid caller number");
            System.exit(1);
        }
        return this.callerNumber;
    }
    /**
     *Returns the validated <code>String</code> calleeNumber of current PhoneCall Object.
     *
     * @return validated <code>String</code> calleeNumber
     */
    @Override
    public String getCallee() {
        String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
        if (!this.calleeNumber.matches(phoneNumberPattern)){
            System.err.println("Invalid callee number"); System.exit(1);}
        return this.calleeNumber;
    }
    /**
     * Returns the <code>String</code> startTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> startTimeString
     */
    @Override
    public String getStartTimeString() {
        int f= DateFormat.SHORT;
        DateFormat df = DateFormat.getDateTimeInstance(f,f,Locale.ENGLISH);
        Date dt = getStartTime();
        startTimeString= df.format(dt);
        return startTimeString;

    }
    /**
     * Returns the <code>String</code> endTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> endTimeString
     */
    @Override
    public String getEndTimeString() {
        int f= DateFormat.SHORT;
        DateFormat df = DateFormat.getDateTimeInstance(f, f, Locale.ENGLISH);
        Date dt = getEndTime();
        endTimeString = df.format(dt);
        return endTimeString;
    }

    @Override
    public Date getEndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        dateFormat.setLenient(false);
        Date newDate= null;
        try {
            newDate = dateFormat.parse(this.endTimeString);
        } catch (ParseException e) {
            System.err.println("Invalid End Time \n"+ e);
            System.exit(1);
            e.printStackTrace();}
        return newDate;
    }

    @Override
    public Date getStartTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        dateFormat.setLenient(false);
        Date newDate= null;
        try {
           newDate = dateFormat.parse(this.startTimeString);
        } catch (ParseException e) {
            System.err.println("Invalid Start Time \n "+ e);
            System.exit(1);
            e.printStackTrace();}
        return newDate;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param c2 the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(PhoneCall c2) {
        return this.getStartTime().compareTo(c2.getStartTime());
    }
    public boolean equals(Object o){
        if(o instanceof PhoneCall){
            PhoneCall other = (PhoneCall) o;
            return this.getStartTime().equals(other.getStartTime());
        }
        return false;
    }
    public int hashCode(){
        return this.getStartTime().hashCode();
    }
}
