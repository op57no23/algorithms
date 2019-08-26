public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;

	private class Node {
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
			else {oldFirst.previous = first}
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
			else {oldLast.next = last}
			n++;
	}

    // remove and return the item from the front
    public Item removeFirst(){
			if (isEmpty()) {throw new NoSuchElementException("deque is empty");}
			Node<Item> oldFirst = first;
			first = oldFirst.next;
			first.previous = null;
			n--;
			if (isEmpty()) {
					last.item = null;
					last.previous = null;
			}
			return oldFirst.item;
	}

    // remove and return the item from the back
    public Item removeLast(){
			if (isEmpty()) {throw new NoSuchElementException("deque is empty");}
			Node<Item> oldLast = last;
			last = oldLast.previous;
			last.next = null;
			n--;
			if (isEmpty()) {
					first.item = null;
					first.next = null;
			}
			return oldLast.item;
	}

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
	}

    // unit testing (required)
    public static void main(String[] args){
			Deque<char> test = new Deque<char>();
			test.addFirst('c');
			test.addLast('l');
			test.addLast('m');
			System.out.println(test.size());
			System.out.println(test.removeFirst());
			System.out.println(test.removeLast());
			System.out.println(test.removeFirst());
			System.out.println(test.isEmpty());
			System.out.println(removeFirst());
			
	}
