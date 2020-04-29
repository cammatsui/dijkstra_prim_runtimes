public class MatrixGraph extends Graph {
    private int n;
    private double[][] backingMatrix;
    private boolean undirected;

    public MatrixGraph(int n) {
        this.n = n;
        this.backingMatrix = new double[n][n];
    }

    public MatrixGraph(int n, boolean undirected) {
        this.n = n;
        this.backingMatrix = new double[n][n];
        this.undirected = undirected;
    }

    // Returns whether or not this graph is directed. O(1).
    public boolean isDirected() {return !undirected;}

    // Add an edge from vertex from to vertex to with weight weight. O(1).
    public void addEdge(int from, int to, double weight) {
        backingMatrix[from][to] = weight;
        if (undirected) backingMatrix[to][from] = weight;
    }

    // Add an edge object.
    public void addEdge(Edge toAdd) {
        int from = toAdd.getFrom();
        int to = toAdd.getTo();
        double weight = toAdd.getWeight();
        addEdge(from, to, weight);
    }
    
    // Remove the edge from vertex from to vertex to. O(1).
    public void removeEdge(int from, int to) {
        backingMatrix[from][to] = 0;
        if (undirected) backingMatrix[to][from] = 0;
    }

    // Return true if there is an edge from vertex from to vertex to, false otherwise. O(1).
    public boolean hasEdge(int from, int to) {
        if (backingMatrix[from][to] != 0) return true;
        return false;
    }

    // Get the weight of the edge from vertex from to vertex to. Returns 0 if there is no edge. O(1).
    public double getWeight(int from, int to) {
        return backingMatrix[from][to];
    }

    // Return a list of Edges who point to the vertex vertex. O(V).
    public MyList<Edge> inEdges(int vertex) {
        MyList<Edge> inEdgeList = new MyList<>();
        for (int i = 0; i < this.n; i++) {
            if (backingMatrix[i][vertex] != 0) {
                Edge inEdge = new Edge(i, vertex, backingMatrix[i][vertex]);
                inEdgeList.append(inEdge);
            }
        }
        return inEdgeList;
    }

    // Return a list of Edges who point out of the vertex vertex. O(V).
    public MyList<Edge> outEdges(int vertex) {
        MyList<Edge> outEdgeList = new MyList<>();
        for (int i = 0; i < this.n; i++) {
            if (backingMatrix[vertex][i] != 0) {
                Edge outEdge = new Edge(vertex, i, backingMatrix[vertex][i]);
                outEdgeList.append(outEdge);
            }
        }
        return outEdgeList;
    }

    // Returns the size of the graph. O(1).
    public int getSize() {return n;}
}