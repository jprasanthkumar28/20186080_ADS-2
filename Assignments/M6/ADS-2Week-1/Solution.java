import java.util.Scanner;
import java.util.Arrays;
class PageRank {
	/**
	 * digraph varible.
	 */
	private Digraph digraph;
	private int vertices;
	private int edges;
	PageRank(final Digraph digraph1) {
		this.digraph = new Digraph(digraph1);
		// int count = digraph.outDegreeCount();
	}
	public double getPR() {
		// digraph.Iterable(v);
		int count = 0;
		for (vertices = 0; vertices < 1000; vertices++) {
			count = digraph.outDegreeCount();
		}
		return count;
	}
	/**
     * to display the graph.
     */
    public String toString() {
    	String str = "";
        Digraph digraph = new Digraph(vertices);
        if (digraph.outDegreeCount() == 0) {
            digraph.addEdge(vertices, edges);
        } else {
        	str = vertices +"-" + getPR();
            System.out.println(str);
        }
        return str;
    }
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
        int vertices = Integer.parseInt(scan.nextLine());
        Digraph digraph = new Digraph(vertices);
		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
	    while (vertices > 0) {
	        String[] tokens = scan.nextLine().split(" ");
	        // System.out.println(Arrays.toString(tokens));
	        int v = Integer.parseInt(tokens[0]);
	        int w = Integer.parseInt(tokens[1]);
	        digraph.addEdge(v, w);
	        vertices--;
        }
		
		// Create page rank object and pass the graph object to the constructor
		PageRank p = new PageRank(digraph);
		// print the page rank object
		p.getPR();
		p.toString();	
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
