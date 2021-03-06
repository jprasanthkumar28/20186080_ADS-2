import java.util.Scanner;
import java.util.Arrays;
public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgeWeight
         = new EdgeWeightedGraph(vertices);
        EdgeWeightedDigraph edgeWeightDi
         = new EdgeWeightedDigraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Double.parseDouble(tokens[2]));
            edgeWeight.addEdge(edge);
            // System.out.println(Arrays.toString(tokens));
        }
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
        String caseToGo = scan.nextLine();
        // System.out.println(caseToGo);
		// String caseToGo = null;
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(edgeWeight);
			break;

		case "DirectedPaths":
			String[] strArray = scan.nextLine().split(" ");
   			// System.out.println(Arrays.toString(strArray));
       		DijkstraUndirectedSP dijkstra =
            new DijkstraUndirectedSP(edgeWeight, Integer.parseInt(strArray[0]));
            	// System.out.println("Hi");
            if (dijkstra.hasPathTo(Integer.parseInt(strArray[1]))) {
            	System.out.println(dijkstra.distTo(Integer.parseInt(strArray[1])));
            } else {
         		System.out.println("No Path Found.");
            }
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			String[] strArray1 = scan.nextLine().split(" ");
       		dijkstra = new DijkstraUndirectedSP(edgeWeight, Integer.parseInt(strArray1[1]));
            	// System.out.println("Hi");
            if (dijkstra.hasPathTo(Integer.parseInt(strArray1[2]))) {
            	double data = dijkstra.distTo(Integer.parseInt(strArray1[0]));
            	String str = " ";
            	str = strArray1[0] + " " + strArray1[1];
            	System.out.println(data);
            	System.out.println(str);
            } else {
         		System.out.println("No Path Found.");
            }
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}
