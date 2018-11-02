/**
 * ArrayList from util pacakge.
 */
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * LinearProbingHash ST for noun.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> nounST;
    /**
     * LinearProbingHash ST for id ST.
     */
    private LinearProbingHashST<Integer, String> idST;
    /**
     * for Digraph varaible.
     */
    private Digraph digraph;
    /**
     * for SAP variable.
     */
    private SAP sap;
    /**
     * Gets the digraph.
     *
     * @return     The digraph.
     */
    public Digraph getDigraph() {
        return this.digraph;
    }
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        nounST = new LinearProbingHashST<String, ArrayList<Integer>>();
        idST = new LinearProbingHashST<Integer, String>();
        try {
            In input = new In("./Files/" + synsets);
            int id = 0;
            while (!input.isEmpty()) {
                String line = input.readLine();
                assert !line.equals("");
                String[] tokens = line.split(",");
                id = Integer.parseInt(tokens[0]);
                String[] nouns = tokens[1].split(" ");

                ArrayList<String> nounList = new ArrayList<String>();
                for (String noun : nouns) {
                    nounList.add(noun);
                }
                idST.put(id, tokens[1]);

                for (String noun : nouns) {
                    if (nounST.contains(noun)) {
                        nounST.get(noun).add(id);
                    } else {
                        ArrayList<Integer> s = new ArrayList<Integer>();
                        s.add(id);
                        nounST.put(noun, s);
                    }
                }
            }
        //Hypernyms
            assert id != 1;
            this.digraph = new Digraph(id + 1);

            input = new In("./Files/" + hypernyms);
            while (!input.isEmpty()) {
                String line = input.readLine();
                String[] tokens = line.split(",");

                int syssetIds = Integer.parseInt(tokens[0]);

                for (int i = 1; i < tokens.length; i++) {
                    digraph.addEdge(syssetIds, Integer.parseInt(tokens[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    //graph built
        sap = new SAP(digraph);
    }

    /**
     * to display the graph.
     */
    public void print() {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else if (digraph.outDegreeCount() > 1) {
            throw new IllegalArgumentException("Multiple roots");
        } else {
            System.out.println(digraph.toString());
        }
    }

    /**
     * Iterable function.
     *
     * @return     keys of noun.
     */
    public Iterable<String> nouns() {
        return nounST.keys();
    }

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return nounST.contains(word);
    }
    /**
     * to cal the distance from two nouns.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = nounST.get(nounA);
        ArrayList<Integer> idB = nounST.get(nounB);
        return sap.length(idA, idB);
    }

    /**
     * Sap function.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = nounST.get(nounA);
        ArrayList<Integer> idB = nounST.get(nounB);

        int ancestor = sap.ancestor(idA, idB);
        return idST.get(ancestor);
    }
}
