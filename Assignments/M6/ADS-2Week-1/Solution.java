/**
 * Scanner package.
 */
import java.util.Scanner;
/**
 * Arrays pacakge..
 */
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * digraph varible.
     */
    private Digraph digraph;
    /**
     * reverse digraph.
     */
    private Digraph reverseDigraph;
    /**
     * to store pageranks.
     */
    private double[] pageranks;
    /**
     * vertices variable.
     */
    private double vertices;
    /**
     * page rank constructer.
     *
     * @param      digraph1  The digraph 1
     */
    PageRank(final Digraph digraph1) {
        digraph = digraph1;
        pageranks = new double[digraph1.vertices()];
    }
    /**
     * Gets the page Eank.
     *
     * @param      v     { parameter_description }
     *
     * @return     The pr.
     */
    public double getPR(final int v) {
        // To reverse a diagraph.
        //Iterate it for 1000 times.
        for (int i = 0; i < digraph.vertices(); i++) {
            if (digraph.outdegree(i) == 0) {
                for (int j = 0; j < digraph.vertices(); j++) {
                    if (i != j) {
                        digraph.addEdge(i, j);
                    }
                }
            }
        }
        reverseDigraph = digraph.reverse();
        for (int i = 0; i < pageranks.length; i++) {
            pageranks[i] = 1 / (double) digraph.vertices();
        }
        // System.out.println(Arrays.toString(pageranks));
        final int value = 1000;
        double[] tempArray = new double[digraph.vertices()];
        for (int i = 0; i < value; i++) {
            //Iterate it for every node
            for (int j = 0; j < digraph.vertices(); j++) {
                double temp = 0.0;
                //adjacency vertices.
                for (int k : reverseDigraph.adj(j)) {
                    temp = temp
                    + pageranks[k] / (double) (digraph.outdegree(k));
                }
                tempArray[j] = temp;
            }
            pageranks = Arrays.copyOf(tempArray, tempArray.length);
        }
        return pageranks[v];
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        double temp = getPR(0);
        String s = "";
        for (int v = 0; v < pageranks.length; v++) {
            s = s + v + " - " + pageranks[v] + "\n";
        }
        return s;
    }
}

/**
 * Class for web search.
 */
class WebSearch {
    //Wordnet class.
}

/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
        //EMpty constructer.
    }
    /**
     * Client function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        // read the first line of the input
        //to get the number of vertices
        int vertices = Integer.parseInt(scan.nextLine());
        Digraph digraph2 = new Digraph(vertices);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < vertices; i++) {
            String[] tokens = scan.nextLine().split(" ");
            // System.out.println(Arrays.toString(tokens));
            for (int j = 1; j < tokens.length; j++) {
                digraph2.addEdge(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[j]));
            }
        }
        System.out.println(digraph2.toString());
        // Create page rank object and pass the
        // graph object to the constructor
        PageRank p = new PageRank(digraph2);
        // print the page rank object
        // p.toString();
        System.out.println(p);
        // This part is only for the final test case
        // File path to the web content
        String file = "WebContent.txt";
        // instantiate web search object
        // and pass the page rank object
        // and the file path to the constructor
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
