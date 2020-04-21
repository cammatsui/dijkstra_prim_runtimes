public class MatrixGraph extends Graph {
    private int n;
    private double[][] backingMatrix;
    // private Dictionary ... (implement later so that each node is a key)

    public MatrixGraph(int n) {
        this.n = n;
        this.backingMatrix = new double[n][n];
    }

    // Add an edge from vertex from to vertex to with weight weight.
    public void addEdge(int from, int to, double weight) {
        backingMatrix[from][to] = weight;
    }
    
    // Remove the edge from vertex from to vertex to.
    public void removeEdge(int from, int to) {
        backingMatrix[from][to] = 0;
    }

    // Return true if there is an edge from vertex from to vertex to, false otherwise.
    public boolean hasEdge(int from, int to) {
        if (backingMatrix[from][to] != 0) return true;
        return false;
    }

    // Get the weight of the edge from vertex from to vertex to. Returns 0 if there is no edge.
    public double getWeight(int from, int to) {
        return backingMatrix[from][to];
    }

    // Return a list of Edges who point to the vertex vertex.
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

    // Return a list of Edges who point out of the vertex vertex.
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

    // Returns the size of the graph.
    public int getSize() {return n;}
}