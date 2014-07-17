import java.util.Arrays;

/**
 * Created by clark on 7/5/14.
 */
public class Fast
{
    private static boolean dupulicateFinder(Point[] a, int N, Point p, double slp)
    {
        for (int i = 0; i < N; i++)
        {
            if (p.slopeTo(a[i]) == slp) {   return true;    }
        }
        return false;
    }

    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32758);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++)
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        StdDraw.show(0);

        Arrays.sort(points);
        StringBuffer print = new StringBuffer();

        Point[] slopeOrder;
        for (int i = 0; i < N - 3; i++)
        {
            Point p = points[i];
            slopeOrder = Arrays.copyOfRange(points, i + 1, N);
            Arrays.sort(slopeOrder, p.SLOPE_ORDER);

            int size = 1;
            for (int j = 0; j < slopeOrder.length - 2; j += size, size = 1)
            {
                double slope = p.slopeTo(slopeOrder[j]);

                while (j + size < slopeOrder.length && p.slopeTo(slopeOrder[j + size]) == slope)
                {
                    size++;
                }

                if (size < 3 || dupulicateFinder(points, i, p, slope))
                {
                    continue;
                }

                print.append(p + " -> ");
                for (int k = 0; k < size - 1; k++)
                {
                    print.append(slopeOrder[j + k] + " -> ");
                    print.append(slopeOrder[j + size -1] + "\n");
                }

                p.drawTo(slopeOrder[j + size -1]);
                StdDraw.show(0);
            }
        }
        StdOut.print(print);
        StdDraw.show(0);
    }
}
