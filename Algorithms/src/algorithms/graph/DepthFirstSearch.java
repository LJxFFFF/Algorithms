package algorithms.graph;

/**
 * Created by Ljx on 2017/3/21.
 */
public class DepthFirstSearch {
    private boolean[] marked;   //marked[v] = 是否有一条 s-v 路径?
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w: G.adj(v))
            if (!marked[w]) dfs(G, w);
    }

    public boolean marker(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
