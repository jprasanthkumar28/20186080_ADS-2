/**
 * Scanner import statement.
 */
import java.util.Scanner;
/**
 * class for Solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		//Empty Constructer.
	}
	/**
	 * client function. 
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        TST<Integer> tst = new TST<Integer>();
        Scanner scan = new Scanner(System.in);
        String prefix = scan.nextLine();
        int j = 0;
        for (String word : words) {
            SuffixArray suffixArray = new SuffixArray(word);
            for (int i = 0; i < word.length(); i++) {
                tst.put(suffixArray.select(i), j++);
            }
        }
        for (String word : tst.keysWithPrefix(prefix)) {
            System.out.println(word);
        }
    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
