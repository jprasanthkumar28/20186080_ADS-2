import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String synsetsFname = StdIn.readString();
        String hypernymFname = StdIn.readString();
        String str  = StdIn.readString();
        switch (str) {
            case "Graph":
            try {
                WordNet wordnet = new WordNet(synsetsFname, hypernymFname);
                wordnet.print();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // System.out.println("IllegalArgumentException");
            }
            break;
            case "Queries":
                try {
                    WordNet wordnet1 = new WordNet(synsetsFname, hypernymFname);
                    while (StdIn.hasNextLine()) {
                        String line = StdIn.readLine();
                        String[] stringArray = line.split(" ");
                        if (stringArray[0].equals("null")) {
                            throw new IllegalArgumentException("IllegalArgumentException");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("IllegalArgumentException");
                }
                break;
            default:
            break;
        }
    }
}
