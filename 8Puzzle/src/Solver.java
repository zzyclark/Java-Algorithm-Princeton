/**
 * Created by clark on 7/17/14.
 */
public class Solver
{
    private class BoardNode implements Comparable<BoardNode>
    {
        private final Board current;
        private final int mScore;
        private final int numMovs;
        private final BoardNode prev;

        public BoardNode(final Board board, final BoardNode previous, final int moves)
        {
            current = board;
            prev = previous;
            numMovs = moves;
            mScore = current.manhattan();
        }

        public int compareTo(final BoardNode that)
        {
            return mScore + numMovs - (that.mScore + that.numMovs);
        }
    }

    private final MinPQ<BoardNode> alternat = new MinPQ<BoardNode>();
    private final MinPQ<BoardNode> solution = new MinPQ<BoardNode>();
    private boolean solvable = true;

    public Solver(final Board initial)
    {
        final Board twin = initial.twin();
        solution.insert(new BoardNode(initial, null, 0));
        alternat.insert(new BoardNode(twin, null, 0));

        solve();
    }

    public boolean isSolved()
    {
        if(solution.min().current.isGoal()) return true;
        if(alternat.min().current.isGoal()) return true;
        return false;
    }

    public boolean isSolvable()
    {
        return solvable;
    }

    public int moves()
    {
        if (!solvable)  return -1;
        return solution.min().numMovs;
    }

    public Iterable<Board> solution()
    {
        if (!solvable)  return null;

        final Stack<Board> stack = new Stack<Board>();
        for (BoardNode goal = solution.min(); goal != null; goal = goal.prev)
            stack.push(goal.current);
        return stack;
    }

    private void solve()
    {
        BoardNode sol = null;
        BoardNode alt = null;

        while (!isSolved())
        {
            sol = solution.delMin();
            alt = alternat.delMin();

            for (final Board b : sol.current.neighbors())
                if (sol.prev == null || !b.equals(sol.prev.current))
                    solution.insert(new BoardNode(b, sol, sol.numMovs + 1));
            for (final Board b : alt.current.neighbors())
                if (alt.prev == null || !b.equals(alt.prev.current))
                    alternat.insert(new BoardNode(b, alt, alt.numMovs + 1));
        }
        if (alternat.min().current.isGoal())    solvable = false;
    }

    public static void main(final String [ ] args)
    {
        // create initial board from file
        final In in = new In(args[0]);
        final int N = in.readInt();
        final int [ ][ ] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        final Board initial = new Board(blocks);

        // solve the puzzle
        final Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (final Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
