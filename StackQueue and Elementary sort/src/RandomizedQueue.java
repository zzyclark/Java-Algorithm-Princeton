import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by clark on 6/28/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] data;
    private int size = 0;

    public RandomizedQueue()
    {
        data = (Item[]) new Object[1];
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void enqueue(Item item)
    {
        if (item == null)   { throw new NoSuchElementException("item is null"); }
        if (size == data.length)
        {
             resizePlus();
        }
        data[size] = item;
        size++;
    }

    public Item dequeue()
    {
        if (isEmpty())  { throw new NoSuchElementException("no data inside");   }
        int count = StdRandom.uniform(size);
        Item temp = data[count];
        data[count] = data [--size];
        if (size < data.length/4)
        {
            resizeMinus();
        }
        return temp;
    }

    public Item sample()
    {
        if (isEmpty())  {   throw new NoSuchElementException("no data inside"); }
        return data[StdRandom.uniform(size)];
    }

    private void resizePlus()
    {
        Item[] rawData = (Item[]) new Object[data.length*2];
        for (int i = 0; i < size; i++)
        {
            rawData[i] = data[i];
        }
        data = rawData;
    }

    private void resizeMinus()
    {
        Item[] rawdata = (Item[]) new Object[data.length/4];
        for (int i = 0; i <= size; i++)
        {
            rawdata[i] = data[i];
        }
        data = rawdata;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        int current = 0;
        int[] shuffledIndexes = new int[size];

        public boolean hasNext()
        {
            if (current == 0)
            {
                for (int i = 0; i < size; i++)
                {
                    shuffledIndexes[i] = i;
                }
                StdRandom.shuffle(shuffledIndexes);
            }
            return current < size;
        }

        public Item next()
        {
            if (current == 0)
            {
                for (int i = 0; i < size; i++)
                {
                    shuffledIndexes[i] = i;
                }
                StdRandom.shuffle(shuffledIndexes);
            }
            if (current >= size || size == 0)   {   throw new NoSuchElementException("no data inside"); }
            return data[shuffledIndexes[current++]];
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        System.out.println(test.isEmpty());
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);

        Iterator<Integer> testIt = test.iterator();
        Iterator<Integer> testI2 = test.iterator();
        while (testIt.hasNext())
        {
            System.out.println(testIt.next());
        }
        while (testI2.hasNext())
        {
            System.out.println(testI2.next());
        }
    }

}
