/**
 * Created by clark on 6/17/14.
 */
public class Percolation
{
    private WeightedQuickUnionUF union = null;
    private int size = 0;
    //count is used to count how many node has been opened
    public int count = 0;
    //any open node will return true
    private boolean[][] matirx = null;

    public Percolation(int N)
    {
        union = new WeightedQuickUnionUF(N*N+2);
        size = N;
        matirx = new boolean[N][N];
    }

    public void open(int i, int j)
    {
        if(i < 1 || i >size || j < 1 || j > size) {throw new IndexOutOfBoundsException("You index is out of bound");}

        if(isOpen(i, j))    {return;}
        //check if it is a top node and then connect to virtual head
        if(i == 1)  {union.union((i-1)*size+j-1,size*size);}
        //check if it is a bottom node and then connect to virtual bottom
        if(i == size) {union.union((i-1)*size+j-1, size*size+1);}

        matirx[i-1][j-1] = true;
        count++;

        if(i > 1 && isOpen(i-1, j)) {union.union((i-1)*size+j-1, (i-2)*size+j-1);}
        if(i < size && isOpen(i+1, j))  {union.union((i-1)*size+j-1, i*size+j-1);}
        if(j > 1 && isOpen(i, j-1)) {union.union((i-1)*size+j-1, (i-1)*size+j-2);}
        if(j < size && isOpen(i, j+1))  {union.union((i-1)*size+j-1, (i-1)*size+j);}
    }

    public boolean isOpen(int i, int j)
    {
        if(i < 1 || i >size || j < 1 || j > size) {throw new IndexOutOfBoundsException("You index is out of bound");}
        return matirx[i-1][j-1];
    }

    //if the node is connected with any top node, then it is a Full open site
    public boolean isFull(int i, int j)
    {
        if(i < 1 || i >size || j < 1 || j > size) {throw new IndexOutOfBoundsException("You index is out of bound");}
        return union.connected((i-1)*size+j-1, size*size);
    }

    //if any bottom node is a Full open site, which means it connected at least one top node, then a open path is built.
    public boolean percolate()
    {
        return union.connected(size*size, size*size+1);
    }
}
