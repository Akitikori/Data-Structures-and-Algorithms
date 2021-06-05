/* *****************************************************************************
 *  Name:    @Akitikori
 *  NetID:   rakitikori
 *  Precept: P04
 *
 *  Description:  Implements methods to determine percolation in an n
 *                by n grid. This class can be used to create a grid, to open cells,
 *                to check the status of a cell, and more. It's constructor
 *                takes time on the order of n^2, and all other instance methods
 *                take constant time in calling union() and find() functions in
 *                a weighted quick union find object. For more about this object,
 *                check this website: https://algs4.cs.princeton.edu/code/
 *                javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html
 *                I hope to write some code for this later.
 *                Stay tuned...
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // keep an array to track what cells are opened
    private boolean[] open;
    // keep an int that knows how many sites are opened
    private int numberOfOpenSites;
    // next, you want a handy Weighted Quick Union Find object as your data
    // structure
    private WeightedQuickUnionUF wquf;
    // how big is the n for the n by n grid? Answer below:
    private int size;
    // I just kept this variable to make the code easier . This represents the
    // int index of the virtual top node
    private int virtualTop;
    // I just kept this variable to make the code easier . This represents the
    // int index of the virtual bottom node
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // initialize open array to be false with length n-squared
        open = new boolean[n * n];
        // save the number of virtual nodes to be added in a variable
        int virtualNodes = 2;
        // create a WQUF object with n * n + the number of virtual nodes added
        wquf = new WeightedQuickUnionUF(n * n + virtualNodes);
        // Assign the int value of size, virtualTop, and virtualBottom
        size = n;
        virtualTop = size * size;
        virtualBottom = virtualTop + 1;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // get 2-D index of the coordinate
        int index = twoD(row, col);
        // open the location/site
        open[index] = true;
        // if the location is in the top row, make it full and add to virtualTop
        if (index < size) {
            wquf.union(virtualTop, index);
        }
        // if the location is in the bottom row, connect it to virtualBottom
        if (index >= virtualTop - size && index < virtualTop) {
            wquf.union(index, virtualBottom);
        }

        // connect the opened site to each of its neighbouring open sites
        // add left cell
        // first check to make sure you're not in the leftmost column
        if (col > 0) {
            // if the left site is open, add it to the index's network
            if (open[index - 1]) {
                wquf.union(index, index - 1);
            }
        }
        // add right cell
        // first check to make sure you're not in the rightmost column
        if (col < size - 1) {
            // if the right site is open, add it to the index's network
            if (open[index + 1]) {
                wquf.union(index, index + 1);
            }
        }
        // add the cell below if it is open
        // first check to make sure you're not in the bottom row
        if (row < size - 1) {
            // if the site below is open, add it to the index's network
            if (open[index + size]) {
                wquf.union(index, index + size);
            }
        }
        // add top cell
        // first check to make sure you're not in the top row
        if (row > 0) {
            // if the site above is open, add it to the index's network
            if (open[index - size]) {
                wquf.union(index, index - size);
            }
        }
        // increase the recorded number of open sites by 1
        numberOfOpenSites++;
    }

    // is the site (row, col) open?
    // you just need to check the open array which is mapped to the 2D-index
    public boolean isOpen(int row, int col) {
        int index = twoD(row, col);
        return open[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check if the site is connected to the virtual top site
        return wquf.find(twoD(row, col)) == wquf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // check if the virtual top and virtual bottoms are connected
        return wquf.find(virtualTop) == wquf.find(virtualBottom);
    }

    private int twoD(int row, int col) {
        return row * size + col;
    }

    public static void main(String[] args) {
        // some tests I used for the methods
        Percolation p1 = new Percolation(2);
        StdOut.print(p1.isOpen(0, 0));
        p1.open(0, 0);
        StdOut.println(p1.isOpen(0, 0));
        StdOut.println(p1.isOpen(0, 1));
        StdOut.println(p1.isOpen(1, 1));
        StdOut.println(p1.percolates());
        p1.open(1, 0);
        StdOut.println(p1.percolates());
    }
}
