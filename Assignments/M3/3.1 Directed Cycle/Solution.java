/**
 * Directed Cycle.
 */
import java.util.Scanner;
// import java.util.Arrays;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // empty Constructor.
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Digraph directedGraph = new Digraph(vertices);
        while (edges > 0) {
            String[] tokens = scan.nextLine().split(" ");
            // System.out.println(Arrays.toString(tokens));
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            directedGraph.addEdge(a, b);
            edges--;
        }
        DirectedCycle directedCycle = new DirectedCycle(directedGraph);
        if (directedCycle.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
