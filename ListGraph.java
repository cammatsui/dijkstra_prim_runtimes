public class ListGraph extends Graph{
    private int n;
    private MyList<Edge>[] backingArray;
    // private Dictionary ... (implement later so that each node is a key)
    
    public ListGraph(int n) {
        this.n = n;
        this.backingArray = new MyList[n];
        for (int i = 0; i < n; i++) backingArray[i] = new MyList<>();
    }

    // Add an edge from vertex from to vertex to with weight weight.
    public void addEdge(int from, int to, double weight) {
        Edge toAdd = new Edge(from, to, weight);
        backingArray[from].append(toAdd);
    }

    // Remove the edge from vertex from to vertex to.
    public void removeEdge(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) {
                backingArray[from].remove(i);
            }
        }
    }

    // Returns true if there is an edge from vertex from to vertex to, false otherwise.
    public boolean hasEdge(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) {
                return true;
            }
        }
        return false;
    }

    // Get the weight of the edge from vertex from to vertex to. Returns 0 if there is no edge.
    public double getWeight(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) {
                return backingArray[from].get(i).getWeight();
            }
        }
        return 0;
    }

    // Return a list of Edges who point to the vertex vertex.
    public MyList<Edge> inEdges(int vertex) {
        MyList<Edge> inEdgeList = new MyList<>();
        // Loops through each adjacency list.
        for (int i = 0; i < n; i++) {
            MyList<Edge> iOutEdges = backingArray[vertex];
            // In each adjacency list, add any edges that point to vertex vertex.
            for (int j = 0; j < iOutEdges.size(); j++) {
                if (iOutEdges.get(j).getTo() == vertex) {
                    inEdgeList.append(iOutEdges.get(j));
                }
            }
        }
        return inEdgeList;
    }
    
    // Return a list of Edges who point out of the vertex vertex.
    public MyList<Edge> outEdges(int vertex) {
        return backingArray[vertex];
    }
}