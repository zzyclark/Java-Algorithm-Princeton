import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by clark on 6/28/14.
 */
public class Deque<Item> implements Iterable<Item>
{
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node
    {
        private Item item;
        private Node previous;
        private Node next;
    }

    public Deque()
    {
    }

    public boolean isEmpty()    {   return size == 0;   }

    public int size()   {   return size;    }

    public void addFirst(Item item)
    {
        if (item == null) { throw new NullPointerException("item is empty!"); }
        Node temp = new Node();
        temp.previous = null;
        temp.next = first;
        temp.item = item;

        if (!isEmpty())
            first.previous = temp;
        first = temp;
        if (last == null)
            last = first;
        size++;
    }

    public void addLast(Item item)
    {
        if (item == null) { throw new NullPointerException("item is empty!"); }
        Node temp = new Node();
        temp.previous = last;
        temp.next = null;
        temp.item = item;

        if (!isEmpty())
            last.next = temp;
        last = temp;
        if (first == null)
            first = last;
        size++;
    }

    public Item removeFirst()
    {
        if (isEmpty())   { throw new NoSuchElementException("no item inside"); }
        Item temp = first.item;
        if (first.next != null)
           first.next.previous = null;
        if (first == last)
           last = null;
        first = first.next;
        size--;
        return temp;
    }

    public Item removeLast()
    {
        if (isEmpty())   { throw new NoSuchElementException("no item inside"); }
        Item temp = last.item;
        if (last.previous != null)
            last.previous.next = null;
        if (last == first)
            first = null;
        last = last.previous;
        size--;
        return temp;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()    { return current != null;   }
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException("no item inside");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("not supported");
        }
    }

    public static void main(String[] args)
    {
        Deque<Integer> test = new Deque<Integer>();
//        test.removeFirst();
        test.addLast(1);
//        test.addLast(2);
//        test.addLast(3);
        test.addFirst(0);
//        test.removeFirst();
        test.removeLast();
        Iterator<Integer> testIt = test.iterator();
        while (testIt.hasNext())
        {
            System.out.println(testIt.next());
        }
    }
}
