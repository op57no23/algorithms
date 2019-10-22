import edu.princeton.cs.algs4.StdIn;

public class Permutation {
		public static void main(String[] args){
				int k = Integer.parseInt(args[0]);
				String[] a = new String[k];
				RandomizedQueue<String> output = new RandomizedQueue<String>();
				while (!StdIn.isEmpty()){
						output.enqueue(StdIn.readString());
				}
				while (output.size() > k){
						output.dequeue();
				}
				while (!output.isEmpty()){
						a[output.size() - 1] = output.dequeue();
				}
				for (String s : a){
						System.out.println(s);
				}
		}
}
