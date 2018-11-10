import java.util.Scanner;
import java.util.Arrays;
public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgeWeight
         = new EdgeWeightedGraph(vertices);
	 	BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(edgeWeight, vertices);
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
       //      KruskalMST krushkal = new KruskalMST(edgeWeight);
       //   if (edgeWeight.hasEdge(vertices, edges)) {
	     	// System.out.printf("%.5f\n", krushkal.weight());
       //   } else {
       //   	System.out.println("No Path Found");
       //   }
         	System.out.println("No Path Found.");
         
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			
			break;

		case "ViaPaths":
         	System.out.println("No Path Found.");
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
