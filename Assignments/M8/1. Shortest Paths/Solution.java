import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Empty Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokens = scan.nextLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        String[] strArray = scan.nextLine().split(" ");
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(n);
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (int i = 0; i < strArray.length; i++) {
            hash.put(strArray[i], i);
        }
        while (m > 0) {
            String[] array = scan.nextLine().split(" ");
            edgeWeightedGraph.addEdge(new Edge(
                            hash.get(array[0]), hash.get(array[1]),
                            Double.parseDouble(array[2])));
            m--;
        }
        int queries = Integer.parseInt(scan.nextLine());
        while (queries > 0) {
            String[] strArray1 = scan.nextLine().split(" ");
            int a = hash.get(strArray1[0]);
            DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(edgeWeightedGraph, a);
            if (dijkstra.hasPathTo(hash.get(strArray1[1]))) {
                System.out.println((int) dijkstra.distTo(hash.get(strArray1[1])));
            }
            queries--;
        }
    }
}
