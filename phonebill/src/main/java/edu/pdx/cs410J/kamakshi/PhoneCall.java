package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.List;

/**
 * This Class inherits its super class AbstractPhoneCall And its methods.
 * It has call details which will be added to the customer's PhoneBill.
 *
 * @see PhoneBill
 * @author Kamakshi Nagar
 */
public class PhoneCall extends AbstractPhoneCall {

    /**
     * The <code>String</code>  are null.
     */
    String callerNumber = null;
    String calleeNumber = null;
    String startTimeString= null;
    String endTimeString= null;

    /**
     * Returns the <code>String</code> callerNumber of current PhoneCall Object.
     *
     * @return <code>String</code> callerNumber
     */
    @Override
    public String getCaller() {
        return this.callerNumber;
    }
    /**
     * Returns the <code>String</code> calleeNumber of current PhoneCall Object.
     *
     * @return <code>String</code> calleeNumber
     */
    @Override
    public String getCallee() {
        return this.calleeNumber;
    }
    /**
     * Returns the <code>String</code> startTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> startTimeString
     */
    @Override
    public String getStartTimeString() {
        return this.startTimeString;
    }
    /**
     * Returns the <code>String</code> endTimeString of current PhoneCall Object.
     *
     * @return <code>String</code> endTimeString
     */
    @Override
    public String getEndTimeString() {
        return this.endTimeString;
    }
}
