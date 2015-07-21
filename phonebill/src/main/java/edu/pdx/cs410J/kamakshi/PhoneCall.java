package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneCall;

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


    /**
     * creates an empty phone call.
     */
    public PhoneCall() {
    }

    /**
     * Creates a phone call with the all information in it.
     *
     * @param callerNumber phone number of a caller
     * @param calleeNumber phone number  of a callee
     * @param startTimeString start time of call
     * @param endTimeString end time of call
     */
    public PhoneCall(String callerNumber, String calleeNumber, String startTimeString, String endTimeString) {
        this.calleeNumber = calleeNumber;
        this.callerNumber = callerNumber;
        this.startTimeString = startTimeString;
        this.endTimeString = endTimeString;
    }

    /**
     * Returns the validated <code>String</code> callerNumber of current PhoneCall Object.
     *
     * @return  validated <code>String</code> callerNumber
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

    /**
     * Returns the formatted <code>Date</code> StartTime of current PhoneCall Object.
     *
     * @return <code>Date</code>  startTime
     */
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
    /**
     * Returns the formatted <code>Date</code> endTime of current PhoneCall Object.
     *
     * @return <code>Date</code>  endTime
     */
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
     * Compares this Phone Call object with the other Phone call object.Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the other phone call.
     * First it checks with the start time if start time is equal then compares using
     * caller number, if same considers same phone call.
     *
     * @param c2 the other phone call to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(PhoneCall c2) {
        int diff = this.getStartTimeString().compareTo(c2.getStartTimeString());
        if (diff == 0) diff = this.getCaller().compareTo(c2.getCaller());
        return diff;
    }

    /**
     * With Implementation of compareTo() we need to implement equals() and hashcode().
     * @param o other object that need to be compared
     * @return true if both object's hash code are equal and false if not.
     */
    public boolean equals(Object o){
        if(o instanceof PhoneCall){
            return true;
        }
        return false;
    }

    /**
     * Returns <code>int</code> for the hash code
     * @return hashcode
     */
    public int hashCode(){
        return this.getStartTime().hashCode();
    }

    /*public void setCaller(String callerNumber) {
        this.callerNumber= callerNumber;
    }

    public void setCallee(String calleeNumber){
        this.calleeNumber= calleeNumber;
    }

    public void setStartTimeString(Date startTime){
        this.startTime= startTime;
    }
    public void setEndTime(Date endTime){
        this.endTime= endTime;
    } */
}
