import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by clark on 7/17/14.
 */
public class Board
{
    private final char[] grid;
    private final int size;
    private final char[] solv;

    public Board(final int[][] blocks)
    {
        size = blocks.length;
        grid = new char[size * size];
        solv = new char[size * size];

        int zero = 0;
        for (int i = 0; i < size * size; i++)
        {
            grid[i] = (char) blocks[i / size][i % size];
            solv[i] = (char) blocks[i / size][i % size];
            if (grid[i] == 0) zero = i;
        }

        final char swap = solv[zero];
        solv[zero] = solv[size * size - 1];
        solv[size * size - 1] = solv[zero];
        Arrays.sort(solv, 0, size * size - 1);
    }

    public int hamming()
    {
        int count = 0;
        for (int i = 0; i < size * size; i++)
        {
            if (grid[i] != 0 && grid[i] != solv[i]) count++;
        }
        return count;
    }

    public int dimension()
    {
        return size;
    }

    public int manhattan()
    {
        int sum = 0;
        for (int i = 0; i < size * size; i++)
        {
            if (grid[i] != 0 && grid[i] != solv[i])
            {
                final int pos = Arrays.binarySearch(solv, grid[i]);
                final int rowdiff = pos / size - i / size;
                final int coldiff = pos % size - i / size;
                sum = sum + rowdiff + coldiff;
            }
        }
        return sum;
    }

    public boolean isGoal()
    {
        return hamming() == 0 && manhattan() == 0;
    }

    public Board twin()
    {
        int[][] newBlocks = new int[size][size];
        for (int i = 0; i < size * size; i++)
        {
            newBlocks[i / size][i % size] = grid[i];
        }
        int row = StdRandom.uniform(0, size);
        while (newBlocks[row][0] == 0 || newBlocks[row][1] == 0)
            row = StdRandom.uniform(0, size);
        swap(newBlocks, row, 0, row, 1);
        return new Board(newBlocks);
    }

    public boolean equals(final Object y)
    {
        if (y == this) return true;
        if (y == null) return false;

        if (y.getClass() != this.getClass()) return false;
        final Board that = (Board) y;
        return that.size == size && Arrays.equals(that.grid, grid);
    }

    public Iterable<Board> neighbors()
    {
        final Stack<Board> stack = new Stack<Board>();
        int pos = 0;
        final int[][] copy = new int[size][size];
        for (int i = 0; i < size * size; i++)
        {
            copy[i / size][i % size] = grid[i];
            if (grid[i] == 0) pos = i;
        }

        //swap with left
        if (pos % size - 1 >= 0)
        {
            swap(copy, pos/size, pos%size, pos/size, pos%size-1);
            stack.push(new Board(copy));
            swap(copy, pos/size, pos%size, pos/size, pos%size-1);
        }
        //swap with right
        if (pos % size + 1 < size)
        {
            swap(copy, pos/size, pos%size, pos/size, pos%size+1);
            stack.push(new Board(copy));
            swap(copy, pos/size, pos%size, pos/size, pos%size+1);
        }
        //swap with up
        if (pos / size -1 >= 0)
        {
            swap(copy, pos/size, pos%size, pos/size-1, pos%size);
            stack.push(new Board(copy));
            swap(copy, pos/size, pos%size, pos/size-1, pos%size);
        }
        //swap with down
        if (pos / size + 1 < size)
        {
            swap(copy, pos/size, pos%size, pos/size+1, pos%size);
            stack.push(new Board(copy));
            swap(copy, pos/size, pos%size, pos/size+1, pos%size);
        }
        return stack;
    }

    private void swap(final int[][] a, final int ix, final int iy, final int jx, final int jy)
    {
        final int swap = a[ix][iy];
        a[ix][iy] = a[jx][jy];
        a[jx][jy] = swap;
    }

    public String toString()
    {
        final StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                s.append(String.format("%2d", (int) grid[i*size+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
