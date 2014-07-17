/**
 * Created by omnitoons on 7/4/14.
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new ByPoint();

    private final int x;
    private final int y;

    // create the point (x, y)
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw()
    {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that)
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that)
    {
        if (that.x == x)
        {
            if (that.y == y)
            {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        return (double) (that.y - y)/(double) (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that)
    {
        if (that == null)   {   throw new RuntimeException();   }
        if (y <= that.y && x < that.x)  {   return  -1; }
        else if (y == that.y && x == that.x) {  return 0;   }
        else    {   return 1;   }
    }

    // return string representation of this point
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    //Comparetor
    private class ByPoint implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            if (p1 == null) {   throw new RuntimeException();  }
            if (p2 == null) {   throw new RuntimeException();  }
            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);
            if (s1 < s2) {   return -1;   }
            else if (s1 == s2)  {   return 0;  }
            else    {   return 1;   }
        }
    }
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
