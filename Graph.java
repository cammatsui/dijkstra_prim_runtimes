import java.util.Random;

// An abstract class. Describes the graph interface and implements
//      Dijkstra's and Prim's algorithms.
public abstract class Graph {
    
    // ===========================================================
    // GRAPH INTERFACE
    public abstract void addEdge(int i, int j, double w);
    public abstract void removeEdge(int i, int j);
    public abstract boolean hasEdge(int i, int j);
    public abstract double getWeight(int i, int j);
    public abstract MyList<Edge> inEdges(int i);
    public abstract MyList<Edge> outEdges(int i);
    public abstract int getSize();
    public abstract boolean isDirected();
    // ===========================================================

    public VertexPath[] dijkstra(int startVertex) {
        // "Routing" table.
        VertexPath[] dijkstraTable = new VertexPath[this.getSize()];
        // List of unvisited vertices. A vertex is visited once all of its neighbors have been examined.
        MyList<VertexPath> unvisited = new MyList<>();
        // Initialize our table and unvisited list.
        for (int i = 0; i < this.getSize(); i++) {
            VertexPath thisVertex = new VertexPath(i);
            // Set initial node's path length to 0.
            if (i == startVertex) thisVertex.setPath(0);
            dijkstraTable[i] = thisVertex;
            unvisited.append(thisVertex);
        }
        // Core of algorithm:
        // 1. Visit the unvisited vertex with the shortest current distance.
        // 2. Get the list of this vertex's neighbors.
        // 3. Check if the path to the vertex we are visiting, plus the weight of the edge from the vertex we're visiting,
        //      is less than that neighbor's current distance from the starting point.
        // 4. If so, update that neighbor's shortest path to the path from the vertex we're visiting by changing both its weight
        //      and previous node.
        // 5. Continue steps 1-4 until every node has been visited.
        while (unvisited.size() > 0) {
            VertexPath visiting = unvisited.popMin();
            double currentDistance = visiting.getPath();
            MyList<Edge> neighbors = outEdges(visiting.getVertex());
            for(int i = 0; i < neighbors.size(); i++) {
                Edge currentEdge = neighbors.get(i);
                double edgeWeight = currentEdge.getWeight();
                if (currentDistance + edgeWeight < dijkstraTable[currentEdge.getTo()].getPath()) {
                    dijkstraTable[currentEdge.getTo()].setPath(currentDistance + edgeWeight);
                    dijkstraTable[currentEdge.getTo()].setPrev(visiting.getVertex());
                }
            }
        }
        // The method returns a table that has an entry for each vertex
        //      that has the length of the shortest path and the previous vertex in that path.
        return dijkstraTable;
    }

    public Graph prim(int startVertex) {
        if (this.isDirected()) {
            // Prim's algorithm assumes an undirected, connected graph.
            throw new RuntimeException("Prim's Algorithm must be run on an undirected graph.");
        }
        // Create an empty graph of the same size. This is what will be returned as the minimum spanning tree.
        Graph g = new MatrixGraph(getSize(), true);
        // Create a list of visisted nodes.
        MyList<Integer> visited = new MyList<>();
        // Create a min heap (min priority queue) to add all of the possible edges to.
        MinEdgeHeap edgeHeap = new MinEdgeHeap();
        // The first vertex we're visiting.
        int currentVertex = startVertex;
        // Core of the algorithm:
        // 1. Mark the current vertex as visited.
        // 2. Add all of that vertex's outedges to the min heap. Pop until you find the minimum
        //      edge that points to an unvisited vertex.
        // 3. Add that minimum edge to our minimum spanning tree.
        // 4. Move the current vertex to the vertex that this minimum edge points to.
        // 5. Repeat until every visitable node has been visited.
        while (visited.size() < getSize()) {
            visited.append(currentVertex);
            // We're done if we've visited all the vertices.
            if (visited.size() == getSize()) break;
            MyList<Edge> out = outEdges(currentVertex);
            while (out.size() > 0)
                edgeHeap.add(out.pop());

            // We're done if there are no more visitable vertices.
            if (edgeHeap.size() == 0) break;

            Edge next = edgeHeap.remove();            
            
            while (visited.in(next.getTo())) {
                next = edgeHeap.remove();
            }
            g.addEdge(next.getFrom(), next.getTo(), next.getWeight());
            currentVertex = next.getTo();
        }
        return g;
    }

    // Returns a random connected, undirected graph to test Prim's algorithm. Runtime could be improved with a well-implemented set,
    //      but I chose this algorithm because it was easy to implement and deterministic.
    public static Graph randUndConGraph(int n) {
        Random r = new Random();
        Graph g = new MatrixGraph(n, true);
        MyList<Integer> nodeList = new MyList<>();
        for (int i = 1; i < n; i++) nodeList.append(i);
        int currentMaxIndex = n-1;
        int currentVertex = 0;
        while (nodeList.size() > 0) {
            // Randomly choose an vertex from the list, place an edge of random weight (0 to 10) from our current vertex to it,
            //      set the current vertex to the randomly chosen vertex, decrement the current max index.
            int nextVertexIndex = r.nextInt(currentMaxIndex);
            int nextVertex = nodeList.pop(nextVertexIndex);

            g.addEdge(currentVertex, nextVertex, randomWeight());
            currentVertex = nextVertex;
            currentMaxIndex -= 1;
        }
        // Randomly add 3 x n edges.
        for (int i = 0; i < 3*n; i++) {
            int randomTo = r.nextInt(n);
            int randomFrom = r.nextInt(n);
            while (randomFrom == randomTo) randomFrom = r.nextInt(n);
            

            g.addEdge(randomFrom, randomTo, randomWeight());
        }

        return g;
    }

    // Returns a random double between 0 (exclusive) and 10 (inclusive) formatted to one decimal place.
    private static double randomWeight() {
        Random r = new Random();
        int intWeight = r.nextInt(101);
        while (intWeight == 0) intWeight = r.nextInt(101);
        double doubleWeight = intWeight;
        return doubleWeight/10;
    }

    // Prints the graph, in an adjacency-lists style.
    public void print() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(i + ": ");
            MyList<Edge> out = outEdges(i);
            int s = out.size();
            for (int j = 0; j < s; j++) {System.out.print(out.get(j).getTo() + ", w = " + out.get(j).getWeight() + "| ");}
            System.out.println();
        }
    }
}