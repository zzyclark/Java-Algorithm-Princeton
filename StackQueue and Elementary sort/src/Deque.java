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
        Item item;
        Node previous;
        Node next;
    }

    public Deque()
    {
    }

    public boolean isEmpty()    {   return size == 0;   }

    public int size()   {   return size;    }

    public void addFirst(Item item)
    {
        if (item == null) { throw new NoSuchElementException("item is empty!"); }
        size++;
        if (first == null)
        {
            first = new Node();
            first.item = item;
            first.previous = null;
            first.next = null;
            return;
        }

        Node tempNode = new Node();
        Node oldFirst = first;
        tempNode.item = item;
        tempNode.previous = null;
        tempNode.next = oldFirst;
        first = tempNode;
    }

    public void addLast(Item item)
    {
        if (item == null) { throw new NoSuchElementException("item is empty!"); }
        size++;
        if (first == null)
        {
            first = new Node();
            first.item = item;
            first.previous = null;
            first.next = null;
        }else if(last == null)
        {
            last = new Node();
            first.next = last;
            last.item = item;
            last.previous = first;
            last.next = null;
        }else
        {
            Node tempNode = new Node();
            tempNode.item = item;
            tempNode.previous = last;
            last.next = tempNode;
            tempNode.next = null;
            last = tempNode;
        }
    }

    public void removeFirst()
    {
       if (isEmpty())   { throw new UnsupportedOperationException("no item inside"); }
       size--;
       first = first.next;
       if (size > 0)   { first.previous = null;    }
       if (size == 1)  { last = null;  }
    }

    public void removeLast()
    {
        if (isEmpty())   { throw new UnsupportedOperationException("no item inside"); }
        size--;
        if (size == 0)   {   first = null;   }
        if (size == 1)
        {
            first.next = null;
            last = null;
        }
        if (size > 1)
        {
            last = last.previous;
            last.next = null;
        }
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
            Item item = current.item;
            current = current.next;
            return item;
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
