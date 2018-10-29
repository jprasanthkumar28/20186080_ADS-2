import java.util.Scanner;
import java.util.Arrays;
/**
 * Interface for graph.
 */
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
/**
 * Class for graph adt.
 */
class GraphADT implements Graph {
	private int V;
    private int E;
    private Bag<Integer>[] adj;
    protected GraphADT() {

    }
    /**
     * Constructs the object.
     *
     * @param      V     { parameter_description }
     */
    public GraphADT(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
	/**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    public void addEdge(int v, int w) {
    	if (v == w) {
        	return;
        }
		if (!hasEdge(v,w)) {
            E++;
            
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
	public boolean hasEdge(int v, int w) {
		for(int k : adj[v]) {
				if (k==w) {
					return true;
				}
		}
		return false;
    }
    public void listdisplay(int V, int E, String[] tokens) throws Exception {
    	if (E <= 1 && V <= 1) {
    		System.out.println(V() + " vertices" + ", " + E() + " edges");
    		throw new Exception("No edges");
    	} else {
    		System.out.println(V() + " vertices" + ", " + E() + " edges");
    		for (int i = 0; i < tokens.length; i++) {
			String str = "";
			str = tokens[i] + ": ";
			for (int k : adj(i)) {
				str = str + tokens[k] + " ";
			}
			System.out.println(str);
			}
    	}
    }

    public void matrixdisplay(int V, int E) throws Exception {
    	if (E <= 1 && V <= 1) {
    		System.out.println(V() + " vertices" + ", " + E() + " edges");
    		throw new Exception("No edges");
    	} else {
    		System.out.println(V() + " vertices" + ", " + E() + " edges");
    		int[][] disp = new int[V][V];
    		for (int i = 0; i  < V; i++) {
    			for (int j = 0; j < V; j++) {
    				if (hasEdge(i, j)) {
    					disp[i][j] = 1;
		    		}
    			}
    		}

    		for (int i = 0; i < V; i++) {
    			for (int j = 0; j < V; j++) {
    				System.out.print(disp[i][j] + " ");
    			}
    			System.out.println();
    		}
    		
    	}
    }
}

public final class Solution {
	protected Solution() {
		//Empty Constructer.
	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		GraphADT graph = new GraphADT();
		String str = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine()); 
		int edges = Integer.parseInt(scan.nextLine());
		String[] data = scan.nextLine().split(",");
		graph = new GraphADT(vertices);
		// System.out.println(Arrays.toString(data));
		while(scan.hasNext()) {
			String connect = scan.nextLine();
			String[] connector = connect.split(" ");
			// System.out.println(Arrays.toString(connector));
			graph.addEdge(Integer.parseInt(connector[0]),
			Integer.parseInt(connector[1]));
		}
		switch(str) {
			case "List":
			try {
				graph.listdisplay(vertices, edges, data);
			} catch (Exception p) {
				System.out.println(p.getMessage());
			}
			break;
			case "Matrix":
			try {
				graph.matrixdisplay(vertices,edges);
			} catch (Exception p) {
				System.out.println(p.getMessage());
			}
			break;
		}
	}
}
