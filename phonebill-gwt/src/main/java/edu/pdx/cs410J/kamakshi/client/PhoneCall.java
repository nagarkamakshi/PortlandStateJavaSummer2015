package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;

import java.util.Date;
import java.util.Locale;

public class PhoneCall extends AbstractPhoneCall implements Comparable<AbstractPhoneCall> {

    String caller = null;
    String callee = null;
    Date startDate = null;
    Date endDate = null;
    String start ;
    String end;

    public PhoneCall() {
    }

    public PhoneCall(String callerNumber, String calleeNumber, String start, String end) {
        this.caller = callerNumber;
        this.callee = calleeNumber;
        this.startDate = new Date();
        this.endDate = new Date();
        this.start= start;
        this.end = end;

    }

    @Override
    public String getCaller() {
        return this.caller;
    }

    @Override
    public Date getStartTime() {
        Date newDate= null;
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
            newDate = dateTimeFormat.parse(this.start);
        } catch (Exception e)
        {
           throw new PhoneBillGwtException("Parser error"+ e);
        }
        return newDate;
    }

    public String getStartTimeString() {

       // String dateStr = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm a").format(getStartTime());
        return start;
    }

    @Override
    public String getCallee() {
        return this.callee;
    }

    public Date getEndTime() {
        Date newDate= null;
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
            newDate = dateTimeFormat.parse(this.end);
        } catch (Exception e)
        {
            System.err.println(e);
        }
        return newDate;
    }

    public String getEndTimeString() {
        //String dateStr = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm a").format(getEndTime());
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


}


