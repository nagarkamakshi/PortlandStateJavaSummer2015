package edu.pdx.cs410J.kamakshi;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vaio on 19-06-2015.
 */
public class People extends Human {

    static final String USAGE_MESSAGE = "args are (in this order)\n" +
            " 1. name \n" +
            " 2. gender \n" +
            " 3. date mm/dd/yyyy \n" +
            " 4. time hh:mm \n";
    private final String name;
    private final String gender;
    private static String adjective;
    private final List<String> date;

    public People(String name,String gender,List<String> date) throws IllegalStudentArgumentException{
        super(name);
        this.name= name;
        this.gender = gender;

        this.date = date;
    }

    public String toString(){
        return "This is "+ getGenderBasedPrefix() + name +". " + getGenderBasedPronoun() + " is so " + getGenderBasedAdjective()+"Date is: " + getDate();
    }
    private String getGenderBasedPronoun() {
        if (this.gender.equalsIgnoreCase("male")) {
            return "He";

        } else if(this.gender.equalsIgnoreCase("female")){
            return "She";
        } else {
            return "It";
        }
    }
    private String getGenderBasedPrefix() {
        if (this.gender.equalsIgnoreCase("male")) {
            return "Mr. ";

        } else {
            return "Ms. ";
        }
    }
    private String getGenderBasedAdjective() {
        if (this.gender.equalsIgnoreCase("male")) {
            adjective = "Handsome.";

        } else {
            adjective = "Beautiful.";
        }
        return adjective;
    }

    private String getDate() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.date.get(0)+ " " + this.date.get(1));
                    return sb.toString();
    }

    public static void main(String[] args)
    {
        String name = null;
        String gender = null;
        List<String> date = new ArrayList<>();
        switch(args.length)
        {
            case 0:
                printErrorMessageAndExit("Missing command line arguments");
                break;

            case 1:
                printErrorMessageAndExit("Missing Gender");
                break;
            case 2:
                printErrorMessageAndExit("Missing date");
                break;
            case 3:
                printErrorMessageAndExit("Missing time");
                break;
            default:
                name = args[0];
                gender = args[1];
                for (int i=2 ;i<args.length;i++) {
                    date.add(args[i]);
                }

        }
        printPeopleDescriptionToStandardOut(name, gender,date);
    }

    private static void printPeopleDescriptionToStandardOut(String name, String gender,List<String> date) {
        People ppl;
        try {
            ppl = new People(name,gender,date);

        } catch (IllegalStudentArgumentException ex) {
            printErrorMessageAndExit(ex.getMessage());
            return;
        }

        System.out.print(ppl.toString());
        System.exit(0);
    }


    private static void printErrorMessageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println(USAGE_MESSAGE);
        System.exit(1);
    }


    //System.exit(0);

}


