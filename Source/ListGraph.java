public class ListGraph extends Graph{
    private int n;
    private MyList<Edge>[] backingArray;
    private boolean undirected;
    
    public ListGraph(int n) {
        this.n = n;
        this.backingArray = new MyList[n];
        for (int i = 0; i < n; i++) backingArray[i] = new MyList<>();
        this.undirected = false;
    }

    // By default, the graph is directed. If we want to make it directed, we use this constructor
    //      with "ListGraph(n, true)".
    public ListGraph(int n, boolean undirected) {
        this.n = n;
        this.backingArray = new MyList[n];
        for (int i = 0; i < n; i++) backingArray[i] = new MyList<>();
        this.undirected = undirected;
    }

    // Returns whether or not this graph is directed. O(1).
    public boolean isDirected() {return !undirected;}

    // Add an edge from vertex from to vertex to with weight weight. O(n).
    public void addEdge(int from, int to, double weight) {
        removeEdge(from, to);
        Edge toAdd = new Edge(from, to, weight);
        backingArray[from].append(toAdd);
        if (undirected) {
            toAdd = new Edge(to, from, weight);
            backingArray[to].append(toAdd);
        }
    }

    // Add an edge object.
    public void addEdge(Edge toAdd) {
        removeEdge(toAdd.getFrom(), toAdd.getTo());
        backingArray[toAdd.getFrom()].append(toAdd);
        if (undirected) {
            backingArray[toAdd.getTo()].append(new Edge(toAdd.getTo(), toAdd.getFrom(), toAdd.getWeight()));
        }
    }

    // Remove the edge from vertex from to vertex to. O(deg(from)) (directed), O(deg(from) + deg(to)) (undirected).
    public void removeEdge(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) backingArray[from].remove(i);
        }
        if (undirected) {
            for (int i = 0; i < backingArray[to].size(); i++) {
                if (backingArray[to].get(i).getFrom() == from) backingArray[to].remove(i);
            }
        }
    }

    // Returns true if there is an edge from vertex from to vertex to, false otherwise. O(deg(from)).
    public boolean hasEdge(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) return true;
        }
        return false;
    }

    // Get the weight of the edge from vertex from to vertex to. Returns 0 if there is no edge. O(deg(from)).
    public double getWeight(int from, int to) {
        for (int i = 0; i < backingArray[from].size(); i++) {
            if (backingArray[from].get(i).getTo() == to) return backingArray[from].get(i).getWeight();
        }
        return 0;
    }

    // Return a list of Edges who point to the vertex vertex. O(V+E).
    public MyList<Edge> inEdges(int vertex) {
        MyList<Edge> inEdgeList = new MyList<>();
        // Loops through each adjacency list.
        for (int i = 0; i < n; i++) {
            MyList<Edge> iOutEdges = backingArray[i];
            // In each adjacency list, add any edges that point to vertex vertex.
            for (int j = 0; j < iOutEdges.size(); j++) {
                if (iOutEdges.get(j).getTo() == vertex) inEdgeList.append(iOutEdges.get(j));
            }
        }
        return inEdgeList;
    }
    
    // Return a list of Edges who point out of the vertex vertex. O(1).
    public MyList<Edge> outEdges(int vertex) {return backingArray[vertex];}

    // Returns the size of the graph. O(1).
    public int getSize() {return n;}
}