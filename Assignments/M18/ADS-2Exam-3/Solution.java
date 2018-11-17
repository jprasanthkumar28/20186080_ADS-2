import java.util.*;
import java.util.Scanner;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		for (String word:toReadFile(file) ) {
			word = word.toLowerCase();
			if (st.contains(word)) {
				st.put(word, st.get(word) + 1);
			} else {
				st.put(word, 1);
			}
		}
		return st;
	}
}

class T9 {
	final TST<Integer> tst;
	// TST tst = new TST<Integer>();
	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST<Integer>();
		Iterable<String> s = st.keys();
		for (String str: s) {
			tst.put(str, st.get(str));
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		if (tst.hasPrefix(prefix)) {
			return tst.keysWithPrefix(prefix);
		}
		return null;
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		BinarySearchST<Integer, String>  bst = new BinarySearchST<Integer, String>();
		for (String str : words) {
			Integer fre = tst.get(str);
			bst.put(fre, str);
		}
		Bag<String> bag = new Bag<String>();
		String[] array = new String[k];
		for (int j =0; j < k ;j++ ) {
			Integer i = bst.max();
			array[j] = bst.get(i);
			bst.deleteMax();
		}
		Arrays.sort(array);
		for (int m = k; m > 0 ;m--) {
			bag.add(array[m-1]);
		}
		return bag;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
