package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class inherits its super class AbstractPhoneBill and its methods.
 * It has customer Name for whom the bill is to be generated.
 *
 * @see PhoneCall
 * @author Kamakshi Nagar
 */
public class PhoneBill extends AbstractPhoneBill {

    /**
     * The <code>String</code> is null.
     * <code>ArrayList</code> of phone calls is collection of all calls.
     */
    String customerName = null;
    private ArrayList<AbstractPhoneCall> phoneCalls;

    /**
     * creates a Phone empty <code>ArrayList</code> of calls.
     */
    public PhoneBill(){ this.phoneCalls = new ArrayList<>(); }

    /**
     * One Argument Constructor of PhoneBill Class with phone calls
     * @param customerName name of the customer
     */
    public PhoneBill(String customerName){
         this.phoneCalls = new ArrayList<>();
        this.customerName= customerName;
    }

    /**
     * setter of the customer name.
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *  Returns the <code>String</code> customerName of current PhoneBill Object.
     *
     * @return <code>String</code> customerNumber
     */
    @Override
    public String getCustomer() {
        return this.customerName;
    }

    /**
     * This method add a PhoneCall by calling <code>toString()</code> method of AbstractPhoneCall class.
     * @param abstractPhoneCall Its the type of AbstractPhoneCall class, will be used to call toString() method.
     */
    @Override
    public void addPhoneCall(AbstractPhoneCall abstractPhoneCall) {
        this.phoneCalls.add(abstractPhoneCall);
    }

    /**
     * This method returns the collection of phone calls.
     * @return collection of phone calls.
     */

    @Override
    public Collection getPhoneCalls() {
        return this.phoneCalls;
    }


}
