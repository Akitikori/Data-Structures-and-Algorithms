/* *****************************************************************************
 *  Name:    Akitikori
 *  NetID:   rakitikori
 *  Precept: P04
 *
 *  Description:  To be written
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        // store number of terms to print from StdIn
        int n = Integer.parseInt(args[0]);
        // create Randomized Queue object
        RandomizedQueue<String> randy = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randy.enqueue(StdIn.readString());
        }
        for (String s : randy) {
            if (n-- > 0) {
                StdOut.println(s);
            }
        }
    }
}
