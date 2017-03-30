package algorithms.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by Ljx on 2017/3/21.
 */
public class Graph {
    private final int V;    //顶点数目
    private int E;          //边的数目
    private Bag<Integer>[] adj;//邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  //创建邻接表
        for (int v = 0; v < V; v++) {       //将所有链表初始化为空
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    /**
     * 添加边
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     *
     * @param v
     * @return 和v相邻的所有顶点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
