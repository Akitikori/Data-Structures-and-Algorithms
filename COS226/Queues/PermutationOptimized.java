/* *****************************************************************************
 *  Name:    Akitikori
 *  NetID:   rakitikori
 *  Precept: P04
 *
 *  Description:  The Reservoir sampling algorithms could solve the problem:
                  Reservoir sampling is a family of randomized algorithms
                  for randomly choosing a sample of k items from a list S
                  containing n items, where n is either a very
                  large or unknown number. Typically n is large enough that
                  the list doesn’t fit into main memory.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PermutationOptimized {
    public static void main(String[] args) {
        // store number of terms to print from StdIn
        int k = Integer.parseInt(args[0]);
        // store the number of items read from StdIn
        int n = 0;
        // create Randomized Queue object
        RandomizedQueue<String> randy = new RandomizedQueue<>();
        // keep the first n items in randomized queue
        for (int i = 0; i < k; i++) {
            randy.enqueue(StdIn.readString());
            n += 1;
        }

        /** Reservoir Sampling
         * 1. Keep the first k items in memory.
         2. When the i-th item arrives (for $i>k$):
         * with probability $k/i$, keep the new item
         and discard an old one,
         selecting which to replace at random,
         each with chance $1/k$.
         * with probability $1-k/i$, ignore the new one
         */
        // I got it from
        // https://github.com/congchan/algs4/blob/master/queues/src/Permutation.java
        while (!StdIn.isEmpty()) {
            if (StdRandom.uniform(1) < k / n) {
                randy.dequeue();
                randy.enqueue(StdIn.readString());
            }
        }
        for (String s : randy) {
            StdOut.println(s);
        }

    }
}
