package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final int[] stats;
    private final PercolationFactory percolationFactory;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        stats = new int[T];
        percolationFactory = pf;

        for (int i = 0; i < T; i ++){
            int stat = CalculatePercolation(N);
            stats[i] = stat;
        }
    }

    private int CalculatePercolation(int N){
        Percolation percolation = percolationFactory.make(N);

        while(true){
            int r = StdRandom.uniform(N);
            int c = StdRandom.uniform(N);
            percolation.open(r,c);

            if(percolation.percolates())
                return percolation.numberOfOpenSites();
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean()     {
        return StdStats.mean(stats);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(stats);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow() {
        double cl = mean() - 1.96 * stddev() / Math.sqrt(stats.length);
        return cl;
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh(){
        double ch = mean() + 1.96 * stddev() / Math.sqrt(stats.length);
        return ch;
    }

    public static void main(String[] args){
        int n = 100;
        int t = 1000;
        var pf = new PercolationFactory();

        var sw = new Stopwatch();
        var ps = new PercolationStats(n, t, pf);
        var elt = sw.elapsedTime();

        var mean = ps.mean();
        var std = ps.stddev();
        var cfl = ps.confidenceLow();
        var cfh = ps.confidenceHigh();
    }
}
