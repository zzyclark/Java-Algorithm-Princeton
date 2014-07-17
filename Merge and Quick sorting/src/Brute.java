/**
 * Created by clark on 7/5/14.
 */
public class Brute
{
    private static boolean isLine(Point p, Point q, Point r, Point s)
    {
        double slope = p.slopeTo(q);
        return q.slopeTo(r) == slope && r.slopeTo(s) == slope;
    }

    public static void main(String[] args)
    {
        //scale the coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        //read input into a point array
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

        //brute test collinear
        Quick.sort(points);
        for (int i = 0; i < N - 3; i++)
        {
            for (int j = i + 1; j < N - 2; j++)
            {
                for (int k = j + 1; k < N - 1; k++)
                {
                    for (int l = k + 1; l < N; l++)
                    {
                        if (isLine(points[i], points[j], points[k], points[l]))
                        {
                            StdOut.print(points[i] + " -> ");
                            StdOut.print(points[j] + " -> ");
                            StdOut.print(points[k] + " -> ");
                            StdOut.print(points[l] + " -> ");
                            points[i].drawTo(points[l]);
                        }
                    }
                }
            }
        }
        StdDraw.show();
    }
}





