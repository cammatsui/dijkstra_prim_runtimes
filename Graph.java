public abstract class Graph {
    public abstract void addEdge(int i, int j, double w);
    public abstract void removeEdge(int i, int j);
    public abstract boolean hasEdge(int i, int j);
    public abstract double getWeight(int i, int j);
    public abstract MyList<Edge> inEdges(int i);
    public abstract MyList<Edge> outEdges(int i);
}