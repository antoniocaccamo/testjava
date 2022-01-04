package me.antoniocaccamo.testjava;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author antoniocaccamo  on 04/01/2022
 */
public class SortChars {

    private static final Logger logger = Logger.getLogger(SortChars.class.getName());


    private static String inputs = "freccia,doccia";

    public static void main(String[] args) {
        String[] x = inputs.split(",");
        String output[] = new String[x.length];

        for ( int i =0; i < x.length; i++) {
            String s = x[i];
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            output[i] = new String(chars);
        }

        for(String s : output){
            logger.info(s);
        }
    }
    

}