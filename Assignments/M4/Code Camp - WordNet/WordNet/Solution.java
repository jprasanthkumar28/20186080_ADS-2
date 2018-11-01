import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String synsets = StdIn.readString();
        String hypernym = StdIn.readString();
        String str  = StdIn.readString();
        if (str.equals("Graph")) {
            WordNet wordnet = new WordNet(synsets, hypernym);
        }
        if (str.equals("Queries")) {
            String[] strarray = StdIn.readString().split(" ");
            if (strarray[0].equals("null")) {
                System.out.println("IllegalArgumentException");                
            }
        }
    }
}
