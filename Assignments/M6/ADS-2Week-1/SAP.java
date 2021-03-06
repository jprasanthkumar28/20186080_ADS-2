/**
 * Class for sap.
 */
public class SAP {
    /**
     * { var_description }.
     */
    private Digraph digraph;
    /**
     * { var_description }.
     */
    private BreadthFirstDirectedPaths[] bfs;

    /**
    * constructor takes a digraph (not necessarily a DAG).
    Constructs the object.
    @param      digraph1  The digraph 1
    */
    public SAP(final Digraph digraph1) {
        this.digraph = new Digraph(digraph1);
        bfs = new BreadthFirstDirectedPaths[this.digraph.vertices()];
    }
    /**
     * length of shortest ancestral path between v and w.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        if (v < 0 || v > digraph.vertices() - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (w < 0 || w > digraph.vertices() - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }

        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }

        int length = Integer.MAX_VALUE;

        for (int i = 0; i < digraph.vertices(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                }
            }
        }
        // save memory
        bfs[v] = null;
        bfs[w] = null;

        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral.
     * path; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        if (v < 0 || v > digraph.vertices() - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (w < 0 || w > digraph.vertices() - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }

        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }

        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i = 0; i < digraph.vertices(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                    ancestor = i;
                }
            }
        }

        // save memory
        bfs[v] = null;
        bfs[w] = null;

        return ancestor;
    }

    /**
     * length of shortest ancestral path between any vertex in v and any.
     * vertex in w; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }

        int length = Integer.MAX_VALUE;
        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                }
            }
        }

        assert length != -1;

        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * a common ancestor that participates in shortest ancestral path.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v, final
                        Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }

        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                    ancestor = ancestor(i, j);
                }
            }
        }
        assert length != -1;
        return ancestor;
    }
}
