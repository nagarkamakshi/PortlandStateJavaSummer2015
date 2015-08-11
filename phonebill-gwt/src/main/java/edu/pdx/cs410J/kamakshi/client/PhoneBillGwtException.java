package edu.pdx.cs410J.kamakshi.client;

/**
 * This class is for PhoneBillGWT specific Exception and
 * extends <code>RunTimeException<code>.
 * @author Kamakshi Nagar
 */

public class PhoneBillGwtException extends RuntimeException {
    public PhoneBillGwtException(String message){
        super(message);
    }

    public PhoneBillGwtException() {
    }
}
