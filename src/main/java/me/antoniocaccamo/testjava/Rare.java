package me.antoniocaccamo.testjava;


import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * @author antoniocaccamo  on 19/10/2020
 */
public class Rare {

    private static final Logger logger = Logger.getLogger(Rare.class.getName());

    public static int nthMostRare(int[] elements, int n) {
       TreeMap<Integer, Integer> rares = new TreeMap<>();

        for ( int el: elements) {
            Integer k = Integer.valueOf(el);
            if ( rares.containsKey(k) )
                rares.computeIfPresent(k, (integer, integer2) -> integer2 + 1);
            else
                rares.computeIfAbsent(k, integer -> 1);
        }
        logger.info(rares.toString());
        return rares.computeIfAbsent(n, integer -> 0);
    }

    public static void main(String[] args) {
        int x = nthMostRare(new int[] { 5, 4, 3, 2, 1, 5, 4, 3, 2, 5, 4, 3, 5, 4, 5, 6 }, 2);
        System.out.println(x);
    }

}
