/**
 * Created by clark on 6/24/14.
 */
public class test
{
    public static void main(String[] args)
    {
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(10);
        QuickFindUF FUF = new QuickFindUF(10);
        FUF.union(7, 4);
        FUF.union(9, 0);
        FUF.union(4, 5);
        FUF.union(4, 8);
        FUF.union(3, 8);
        FUF.union(6, 0);
        UF.union(9, 7);
        UF.union(5, 1);
        UF.union(2, 8);
        UF.union(6, 3);
        UF.union(5, 8);
        UF.union(3, 7);
        UF.union(8, 4);
        UF.union(8, 0);
        UF.union(9, 0);
        System.out.println("Finished");
    }
}
