/**
 * Created by clark on 6/17/14.
 */
public class PercolationStats
{
    private double[] data = null;
    private int times = 0;

    public PercolationStats(int N, int T)
    {
        Percolation percolation = null;
        if (N <= 0 || T <= 0)   {   throw new IllegalArgumentException("Invalid input"); }
        data = new double[T];
        this.times = T;
        for (int i = 0; i < T; i++)
        {
            int count = 0, k = 0, j = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates())
            {
                k = StdRandom.uniform(1, N + 1);
                j = StdRandom.uniform(1, N + 1);
                if (percolation.isOpen(k, j))    { continue; }
                else
                {
                    percolation.open(k, j);
                    count++;
                }
            }
            data[i] = (double) count / (double) (N * N);
        }
    }

    public double mean()
    {
        return StdStats.mean(data);
    }

    public double stddev()
    {
       return StdStats.stddev(data);
    }

    public double confidenceLo()
    {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / (Math.sqrt(times));
    }

    public double confidenceHi()
    {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / (Math.sqrt(times));
    }

    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        int T = StdIn.readInt();

        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(N, T);

        System.out.println("mean                    =" + percolationStats.mean());
        System.out.println("stddev                  =" + percolationStats.stddev());
        System.out.println("95% confidence interval =" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
        System.out.println("Running time is: " + stopwatch.elapsedTime());
    }
}
