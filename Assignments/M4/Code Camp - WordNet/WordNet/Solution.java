import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String synsets = StdIn.readString();
        String hypernym = StdIn.readString();
        // String str  = StdIn.readString();
        // WordNet wordnet = new WordNet();
        WordNet wordnet = new WordNet(synsets, hypernym);
        // switch (str) {
        //     case "Graph":
        //         break;
        //     case "Queries":
        //         break;
        //     default:
        //         break;
        // }
    }
}
