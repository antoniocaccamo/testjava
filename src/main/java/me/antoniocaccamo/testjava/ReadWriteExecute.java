package me.antoniocaccamo.testjava;

import java.util.logging.Logger;

/**
 * @author antoniocaccamo  on 19/10/2020
 */
public class ReadWriteExecute {

    private static final Logger logger = Logger.getLogger(Rare.class.getName());

    private static final char[] SRWX =  { 'r','w', 'x' };
    private static final int[]    IRWX =  {4, 2, 1 };

    private static final int STEP = 3;

    public static int symbolicToOctal(String permString) {
        int idx = 0;
        int oct = 0;

        if ( permString.length() > 0 && permString.length() % STEP == 0) {

            while (idx  < permString.length() ) {
                int count = 0;
                String block = permString.substring(idx, (idx + STEP));
                for ( int i = 0 ; i < STEP ; i++) {
                    if ( SRWX[i] ==  block.charAt(i) ) {
                        count += IRWX[i];
                    }
                }
                idx += STEP;
                oct = oct * 10 + count;
            }
        }
        return oct;
    }

    public static void main(String[] args) {
        // Should write 752
        logger.info( String.format("%d", ReadWriteExecute.symbolicToOctal("rwxr-x-w-")));
    }
}
