import java.util.*;
public class WordNet {

    // constructor takes the name of the two input files
    private int verticesCount;
    public WordNet(String synsets, String hypernym) {
        // parse(synsets);
        readSynsetFile(synsets, hypernym);
    }
    public void readSynsetFile(String fileName, String hypernym) {
        int id = 0;
        verticesCount = 0;
        try {
            In input = new In("./Files/" + fileName);
            String[] str1 = null;
            while (!input.isEmpty()) {
                verticesCount++;
                String[] tokens = input.readString().split(",");
                id = Integer.parseInt(tokens[0]);
                str1 = tokens[1].split(" ");
            }
            Digraph digraph = new Digraph(verticesCount);
            readHypernymFile(hypernym, digraph);
        } catch (Exception e) {
            System.out.println("not found");
        }
    }
    // // returns all WordNet nouns
    // public Iterable<String> nouns() {
    //     return null;
    // }

    // // is the word a WordNet noun?
    // public boolean isNoun(String word) {
    //     return false;
    // }

    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {
    //     return 0;
    // }

    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB) {
    //     return null;
    // }
       public void readHypernymFile(String hypernyms, Digraph digraph1) {
        try {
            In input1 = new In(".\\Files\\" + hypernyms);
            while (!input1.isEmpty()) {
                String[] tokens1 = input1.readString().split(",");
                for (int i = 1; i < tokens1.length; i++) {
                    digraph1.addEdge(Integer.parseInt(tokens1[0]), Integer.parseInt(tokens1[i]));
                }
            }
            DirectedCycle directedCycle = new DirectedCycle(digraph1);
            int count = 0;
            for (int i = 0; i < verticesCount; i++) {
               if (digraph1.outdegree(i) == 0) {
                   count++;
               }                
           }
            if (count > 1) {
               throw new IllegalArgumentException("Multiple roots");
            }
            if (directedCycle.hasCycle()) {
                // System.out.println("Cycle detected");
               throw new IllegalArgumentException("Cycle detected");
            } else {
                System.out.println(digraph1);
            }    
        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // // do unit testing of this class
    // public static void main(String[] args)
}
