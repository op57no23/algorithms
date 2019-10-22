import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
	private Node<Item> first;
	private Node<Item> last;
	private int n;

	private class Node<Item> {
			private Item item;
			private Node<Item> next;
			private Node<Item> previous;
	}


    // construct an empty deque
    public Deque(){
			first = null;
			last = null;
			n = 0;
	}

    // is the deque empty?
    public boolean isEmpty(){
			return n == 0;
	}

    // return the number of items on the deque
    public int size(){
			return n;
	}

    // add the item to the front
    public void addFirst(Item item){
			if (item == null) {throw new IllegalArgumentException("null argument not allowed");}
			Node<Item> oldFirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldFirst;
			if (isEmpty()) {last = first;}
			else {oldFirst.previous = first;}
			n++;
	}

    // add the item to the back
    public void addLast(Item item){
			if (item == null) {throw new IllegalArgumentException("null argument not allowed");}	
			Node<Item> oldLast = last;
			last = new Node<Item>();
			last.item = item;
			last.previous = oldLast;
			if (isEmpty()) {first = last;}
			else {oldLast.next = last;}
			n++;
	}

    // remove and return the item from the front
    public Item removeFirst(){
			if (isEmpty()) {throw new NoSuchElementException("deque is empty");}
			Item item = first.item;
			if (size() == 1){
					first.next = null;
					first.item = null;
					last.next = null;
					last.item = null;
					n--;
					return item;
			}
			first = first.next;
			first.previous = null;
			n--;
			return item;
	}

    // remove and return the item from the back
    public Item removeLast(){
			if (isEmpty()) {throw new NoSuchElementException("deque is empty");}
			Item item = last.item;
			if (size() == 1){
					first.next = null;
					first.item = null;
					last.next = null;
					last.item = null;
					n--;
					return item;
			}
			last = last.previous;
			last.next = null;
			n--;
			return item;
	}
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
			return new DequeIterator(first);
	}

	private class DequeIterator implements Iterator<Item>{
			private Node<Item> current;

			public DequeIterator(Node<Item> first){
					current = first;
			}

			public boolean hasNext(){
					return (current != null);
			}

			public Item next(){
					if (!hasNext()) {throw new NoSuchElementException();}
					Item item = current.item;
					current = current.next;
					return item;
			}

			public void remove()	{throw new UnsupportedOperationException();}

	}

    // unit testing (required)
    public static void main(String[] args){
			Deque<String> test = new Deque<String>();
			String[] strings = new String[3];
			System.out.println(test.isEmpty());
			strings[0] = "apple";
			strings[1] = "brown";
			strings[2] = "betty";
			test.addFirst(strings[0]);
			test.addLast(strings[1]);
			System.out.println(test.size());
			System.out.println(removeLast());
			System.out.println(removeFirst());
			test.addFirst(strings[2]);
			for (String x : test){System.out.println(x);}			
	}
}
