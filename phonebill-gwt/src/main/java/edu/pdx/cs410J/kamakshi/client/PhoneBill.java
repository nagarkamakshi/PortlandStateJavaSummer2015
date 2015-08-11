package edu.pdx.cs410J.kamakshi.client;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class inherits its super class AbstractPhoneBill and its methods.
 * It has customer Name for whom the bill is to be generated.
 *
 * @author Kamakshi Nagar
 * @see PhoneCall
 */
public class PhoneBill extends AbstractPhoneBill {
    /**
     * The <code>String</code> is Customer Name.
     * <code>ArrayList</code> of phone calls is collection of all calls.
     */
    private Collection<AbstractPhoneCall> calls = new ArrayList<>();
    String customerName;

    /**
     * Returns the <code>String</code> customerName of current PhoneBill Object.
     *
     * @return <code>String</code> customerNumber
     */
    @Override
    public String getCustomer() {
        return this.customerName;
    }

    /**
     * setter of the customer name.
     *
     * @param customerName Name of the cuatomer
     */
    public void setCustomer(String customerName) {
        this.customerName = customerName;
    }


    /**
     * This method add a PhoneCall to the collection
     *
     * @param call Its the type of AbstractPhoneCall class.
     */
    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        this.calls.add(call);
    }

    /** This method returns the collection of phone calls.
     * @return collection of phone calls.
     **/
    @Override
    public Collection getPhoneCalls() {
        return this.calls;
    }
}
