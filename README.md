Java-Algorithm-Princeton
========================

Course related assignment

Percolation
-----------
For Percolation it needs WeightedQuickUnionUF to do QuickUnion. And for other common io API, external jar is needed.

Algorithm related library is algs4.jar, you can downloaded it from [here] [1]

Standard library is stdlib.jar, you can download it from [here] [2]

Further external function mostly will call this two jar file.

Percolation is an simple Monte Carlo simulation.

Stack and Queue
---------------

2 simple Queue implementation

Deque can add and remove item from either first or last

Randomizedqueue remove item randomly from queue 

Subset accept enough string inpurt and an integer size, it will generate the string input base on size with random order.
Currently, Subset use Randomizedqueue with size of inputed string. For future improvement, Subset will only generate a queue base on size input.

**Free Software, Oh Yeah**

[1]:http://algs4.cs.princeton.edu/code/algs4.jar
[2]:http://algs4.cs.princeton.edu/code/stdlib.jar
