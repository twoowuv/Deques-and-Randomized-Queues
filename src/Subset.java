import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * Takes a command line argument as the number of items to print out at random.
 * Then takes in N strings and prints out k of those strings at random.
 * Because we are using the RandomizedQueue they cannot be repeated because they are
 * removed once they are printed.
 */
public class Subset {

    public static void main(String[] args) {

        RandomizedQueue<String> bag = new RandomizedQueue<String>();

        // The number of items to print
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();

            bag.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(bag.dequeue());
        }

    }
}
