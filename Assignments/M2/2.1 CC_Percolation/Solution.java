import java.util.*;
import java.util.Scanner;
/**
 * Class for graph.
 */
class Graph {
    /**
     * matrix declaration.
     */
    private int[][] grid;
    /**
     * declaration of variable.
     */
    private int vertices;
    /**
     * declaration of variable.
     */
    private int edges;
    /**
     * Constructs the object.
     *
     * @param      vertices  The vertices
     */
    Graph(final int vertices) {
        grid = new int[vertices][vertices];
    }
    public int vertices() {
        return vertices;
    }
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                grid[vertexOne][vertexTwo] = 1;
                // grid[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (grid[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    // public void connected(final int v1, final int w1) {
    //     grid[v1][w1] = 1;
    // }
    public int[] adj(final int v) {
        return grid[v];
    }
}
class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graph g, int s) {
        marked = new boolean[g.vertices()];
        id  = new int[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            marked[i] = false;
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    public boolean percolates() {
        if (count > 1) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }
    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }
    /**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(int v) {
        return id[v];
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty Constructor.
    }
    /**
     * {Main method}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String num = scan.nextLine();
        Graph graph = new Graph(Integer.parseInt(num));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] tokens = line.split(" ");
            graph.addEdge(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1);
        }
        CC connected = new CC(graph, Integer.parseInt(num));
        System.out.println(connected.percolates());
    }
}
