package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

/**
 * This Class inherits its super class AbstractPhoneCall And its methods.
 * It has call details which will be added to the customer's PhoneBill.
 *
 * @see PhoneBill
 * @author Kamakshi Nagar
 */

public class PhoneCall extends AbstractPhoneCall implements Comparable<AbstractPhoneCall> {

    /**
     * The <code>String</code>  are null.
     */
    String caller = null;
    String callee = null;
    Date startDate = null;
    Date endDate = null;
    String start=null;
    String end=null;

    public PhoneCall() {
    }

    /**
     * Creates a phone call with the all information in it.
     *
     * @param callerNumber phone number of a caller
     * @param calleeNumber phone number  of a callee
     * @param start start time of call
     * @param end end time of call
     */
    public PhoneCall(String callerNumber, String calleeNumber, String start, String end) {
        this.caller = callerNumber;
        this.callee = calleeNumber;
        this.startDate = new Date();
        this.endDate = new Date();
        this.start = start;
        this.end = end;

    }


    /**
     * Returns the caller number
     * @return caller number
     */
    @Override
    public String getCaller() {
        return this.caller;
    }


    /**
     * Returns the formatted <code>Date</code> StartTime of current PhoneCall Object.
     *
     * @return <code>Date</code>  startTime
     */
    @Override
    public Date getStartTime() {
        Date newDate;
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
            newDate = dateTimeFormat.parse(this.start);
        } catch (Exception e) {
            throw new PhoneBillGwtException("Parser error" + e);
        }
        return newDate;
    }

    /**
     * Returns the <code>String</code> startTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> startTimeString
     */
    @Override
    public String getStartTimeString() {
        return start;
    }

    /**
     * Returns the callee number.
     * @return callee number
     */
    @Override
    public String getCallee() {
        return this.callee;
    }

    /**
     * Returns the formatted <code>Date</code> endTime of current PhoneCall Object.
     *
     * @return <code>Date</code>  endTime
     */
    @Override
    public Date getEndTime() {
        Date newDate = null;
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
            newDate = dateTimeFormat.parse(this.end);
        } catch (Exception e) {
            System.err.println(e);
        }
        return newDate;
    }

    /**
     * Returns the <code>String</code> endTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> endTimeString
     */
    @Override
    public String getEndTimeString() {
        return end;
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
    public int compareTo(AbstractPhoneCall c2) {
        int diff = this.getStartTimeString().compareTo(c2.getStartTimeString());
        if (diff == 0) diff = this.getCaller().compareTo(c2.getCaller());
        return diff;
    }

    /**
     * With Implementation of compareTo() we need to implement equals() and hashcode().
     *
     * @param o other object that need to be compared
     * @return true if both object's hash code are equal and false if not.
     */
    public boolean equals(Object o) {
        return o instanceof PhoneCall;
    }

    /**
     * Returns <code>int</code> for the hash code
     *
     * @return hashcode
     */
    public int hashCode() {
        return this.getStartTime().hashCode();
    }


}


