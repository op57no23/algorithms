import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{

		private int n;
		private Item[] randQ;

		// construct an empty randomized queue
		public RandomizedQueue(){
				randQ = (Item[]) new Object[2];
				n = 0;
		}

		// is the randomized queue empty?
		public boolean isEmpty(){
				return n == 0;
		}

		// return the number of items on the randomized queue
		public int size(){
				return n;
		}

		// add the item
		public void enqueue(Item item){
				if (item == null) {throw new IllegalArgumentException();}
				if (n == randQ.length) {
						resize(randQ.length * 2);
				}
				randQ[n] = item;
				n++;
		}

		// remove and return a random item
		public Item dequeue(){
				if (isEmpty()) {throw new java.util.NoSuchElementException();}
				if (n == randQ.length / 4) {resize(randQ.length / 2);}
				int i = StdRandom.uniform(0,n);
				Item selection = randQ[i];
				exchange(randQ, i, n - 1);
				n--;
				return selection;
		}

		// return a random item (but do not remove it)
		public Item sample(){
				if (isEmpty()) {throw new java.util.NoSuchElementException();}
				return randQ[StdRandom.uniform(0,n)];
		}
	
		// return an independent iterator over items in random order
		public Iterator<Item> iterator(){
				return new randQueueIterator();
		}

		private class randQueueIterator implements Iterator<Item>{
				RandomizedQueue<Item> temp;
				Item currentItem;

				public randQueueIterator(){
						temp = new RandomizedQueue<Item>();
				}

				public boolean hasNext(){
						if (size() > 0) {return true;}
						else if (size() == 0){
								while (temp.size() > 0){
										enqueue(temp.dequeue());
								}
						}
						return false;
				}

				public Item next(){
						if (hasNext()){
								currentItem = dequeue();
								temp.enqueue(currentItem);
								return currentItem;		 
						}
						throw new java.util.NoSuchElementException();
				}

				public void remove(){
						throw new UnsupportedOperationException();
				}
		}

		private void resize(int capacity) {
				assert capacity >= n;

				// textbook implementation
				Item[] temp = (Item[]) new Object[capacity];
				for (int i = 0; i < n; i++) {
						temp[i] = randQ[i];
				}
				randQ = temp;

				// alternative implementation
				// a = java.util.Arrays.copyOf(a, capacity);
		}

		private void exchange(Item[] a, int b, int c){
				Item swap = a[b];
				a[b] = a[c];
				a[c] = swap;
		}

		// unit testing (required)
		public static void main(String[] args){
				RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
				System.out.println(test.isEmpty());
				for (int i = 0; i < 19; i++){
						test.enqueue(i);

				}
				for (int i = 0; i < 9; i++){
						System.out.println(test.dequeue());
				}
				System.out.println(test.size());
				System.out.println(test.sample());
				System.out.println("printing remaining\n");
				for (Integer i : test){
						System.out.println(i);
				}
				System.out.println("reiterating");
				for (Integer i : test){
						System.out.println(i);
						}
		}

}
