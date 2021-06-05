/* *****************************************************************************
 *  Name:    Ada Lovelace
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationWithoutBackwash {
    public boolean[] open;
    public int numberOfOpenSites;
    public WeightedQuickUnionUF wquf;
    public int size;
    public int virtualTop;
    public int virtualBottom;
    public int[] status;
    public boolean percolates;

    // creates n-by-n grid, with all sites initially blocked
    public PercolationWithoutBackwash(int n) {
        // initialize open array to be false with length n-squared
        open = new boolean[n * n];
        int virtualNodes = 2;
        wquf = new WeightedQuickUnionUF(n * n + virtualNodes);
        size = n;
        virtualTop = size * size;
        virtualBottom = virtualTop + 1;
        // initialize status to not connected at all
        // meanings for each of the 4 digits:
        // first 0 is closed/open
        // second 0 is connected to top
        // third 0 is connected to bottom
        status = new int[virtualBottom];
        // set status of sites connected to top and botton
        for (int i = 0; i < size; i++) {
            status[i] = 0b010;
            status[size * size - i - 1] = 0b001;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // get 2-D index of the coordinate
        int index = twoD(row, col);
        // open the location
        open[index] = true;
        status[index] |= 4;
        // if the location is in the top row, make it full and add to tree
        // if (index < size) {
        //   wquf.union(virtualTop, index);
        // }
        // if the location is in the bottom row, connect it to virtualBottom
        // if (index >= virtualTop - size && index < virtualTop) {
        //   wquf.union(index, virtualBottom);
        // }

        // connect the opened site to each of its neighbouring open sites
        // add to left cell
        // first check to make sure you're not in the leftmost column
        if (col > 0) {
            if (open[index - 1]) {
                // temporarily save status of the root of the open cell beside site
                int tempStatus = status[wquf.find(index - 1)];
                // update the status of the index you're at
                status[index] = status[index] | tempStatus;
                wquf.union(index, index - 1);
            }
        }
        // add to right cell
        // first check to make sure you're not in the rightmost column
        if (col < size - 1) {
            // now check if the right cell is open
            if (open[index + 1]) {
                // update status to bitwise OR with root of connected site
                status[index] = status[index] | status[wquf.find(index + 1)];
                wquf.union(index, index + 1);
            }
        }
        // add the cell below if it is open
        // first check to make sure you're not in the bottom row
        if (row < size - 1) {
            // now check if the site below is open
            if (open[index + size]) {
                status[index] = status[index] | status[wquf.find(index + size)];
                wquf.union(index, index + size);
            }
        }
        // add to top cell
        // first check to make sure you're not in the top row
        if (row > 0) {
            // some thoughts
            // if you're in the bottom row, only connect if it is full
            // if (wquf.find())
            // now check if the site below is open
            if (open[index - size]) {
                status[index] = status[index] | status[wquf.find(index - size)];
                wquf.union(index, index - size);
            }
        }

        // update the status of the new root based on what you've learned from
        // existing surrounding open sites
        status[wquf.find(index)] = status[index];
        // increase the recorded number of open sites by 1
        numberOfOpenSites++;
        // check if this new addition percolates by comparing 2 and 3rd status indices
        // I first isolated the second index using bitwise AND with 2
        // then I isolated the 3rd index using bitwise AND with 1, and shifted it by 1 to the left
        int doesPercolate = (status[wquf.find(index)] & 2) & ((status[wquf.find(index)] & 1) << 1);
        if (doesPercolate == 2) {
            percolates = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // check if first item after bitwise manipulation indicates an open cell
        int answer = status[twoD(row, col)] & 4;
        if (answer == 4) {
            return true;
        }
        else return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // int result = (status[twoD(row, col)] & 4) & ((status[twoD(row, col)] & 2) << 1);
        //StdOut.println(status[twoD(row, col)]);
        //StdOut.println(status[twoD(row, col)] & 4);
        // check if site is connected to the first row and open
        //int result = (status[wquf.find(twoD(row, col))] & 4) & (
        //         (status[wquf.find(twoD(row, col))] & 2) << 1);
        //  if (result == 4)
        int result = status[wquf.find(twoD(row, col))];
        if (result == 6 || result == 7) {
            return true;
        }
        else return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    private int twoD(int row, int col) {
        return row * size + col;
    }

    public static void main(String[] args) {
        /* Some unit testing for Percolation creation and methods. Also did some
            testing for the print method I want to use for bitwise operations
        Percolation1 p1 = new Percolation1(2);
        StdOut.print(p1.isOpen(0, 0));
        p1.open(0, 0);
        StdOut.println(p1.isOpen(0, 0));
        StdOut.println(p1.isOpen(0, 1));
        StdOut.println(p1.isOpen(1, 1));
        StdOut.println(p1.percolates());
        p1.open(1, 0);
        StdOut.println(p1.percolates());
        int a = 0b0010;
        int b = 0b0001;
        StdOut.println("a & b = " + Integer.toBinaryString((a | b)));
        */
        // Some testing for integer representation of sites
        int size = 5;
        int counter = 0;
        PercolationWithoutBackwash p1 = new PercolationWithoutBackwash(size);
        for (int i = 0; i < size * size; i++) {
            if (counter < size - 1) {
                StdOut.print(p1.status[i] + " ");
            }
            else if (counter == size - 1) {
                StdOut.println(p1.status[i] + " ");
            }
            counter = (counter + 1) % 5;
            //StdOut.println(counter)
        }
        p1.open(0, 2);
        p1.open(1, 2);
        p1.open(0, 4);
        //StdOut.println(p1.isFull(0, 4));
    }
}
