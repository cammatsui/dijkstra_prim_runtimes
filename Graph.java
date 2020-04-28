import java.util.Random;

// An abstract class. Describes the graph interface and implements
//      Dijkstra's and Prim's algorithms.
public abstract class Graph {
    
    // ===========================================================
    // GRAPH INTERFACE
    public abstract void addEdge(int i, int j, double w);
    public abstract void addEdge(Edge toAdd);
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
        MinVertexPathHeap unvisited = new MinVertexPathHeap();
        // Initialize our table and unvisited list.
        for (int i = 0; i < this.getSize(); i++) {
            VertexPath thisVertex = new VertexPath(i);
            // Set initial node's path length to 0.
            if (i == startVertex) thisVertex.setPath(0);
            dijkstraTable[i] = thisVertex;
            unvisited.add(thisVertex);
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
            VertexPath visiting = unvisited.remove();
            double currentDistance = visiting.getPath();
            MyList<Edge> neighbors = outEdges(visiting.getVertex());
            for(int i = 0; i < neighbors.size(); i++) {
                Edge currentEdge = neighbors.get(i);
                double edgeWeight = currentEdge.getWeight();
                VertexPath to = dijkstraTable[(currentEdge.getTo())];
                if (currentDistance + edgeWeight < to.getPath())
                    unvisited.update(to, currentDistance+edgeWeight, visiting.getVertex());
            }
        }
        // The method returns a table that has an entry for each vertex
        //      that has the length of the shortest path and the previous vertex in that path.
        return dijkstraTable;
    }

    public Graph prim(int startVertex) {
        // Create prim table, list of unvisited vertices and flags
        VertexPath[] primTable = new VertexPath[this.getSize()];
        MinVertexPathHeap unvisited = new MinVertexPathHeap();
        boolean[] unvisitedFlag = new boolean[this.getSize()];

        // Initialize everything
        for (int i = 0; i < this.getSize(); i++) {
            VertexPath thisVertex = new VertexPath(i);
            unvisitedFlag[i] = true;
            if (i == startVertex) thisVertex.setPath(0);
            primTable[i] = thisVertex;
            unvisited.add(thisVertex);
        }

        while (unvisited.size() > 0) {
            // Visit the unvisited node with the smallest known edge.
            VertexPath visiting = unvisited.remove();
            int vert = visiting.getVertex();
            // Mark that we've visited it.
            unvisitedFlag[vert] = false;
            // For each vertex pointed to by an outedge, if the vertex is unvisited and this outedge is smaller,
            //      update the corresponding VertexPath's value and previous vertex.
            MyList<Edge> out = outEdges(vert);
            for (int i = 0; i < out.size(); i++) {
                Edge temp = out.get(i);
                if (unvisitedFlag[temp.getTo()] == true && temp.getWeight() < primTable[temp.getTo()].getPath()) {
                    VertexPath to = primTable[temp.getTo()];
                    unvisited.update(to, temp.getWeight(), temp.getFrom());
                }
            }

        }
    
        Graph mst = new MatrixGraph(getSize(), true);

        // Add the shortest edges from the table to the MST
        for (int i = 0; i < this.getSize(); i++) {
            if (i == startVertex) continue;
            int from = primTable[i].getPrev();
            int to = primTable[i].getVertex();
            double weight = primTable[i].getPath();
            mst.addEdge(from, to, weight);
        }

        return mst;
    }

    // Returns a random directed, not necessarily connected, graph to test Dijkstra's algorithm.
    //      Takes in an empty graph and populates it.
    public static Graph randDirUncGraph(Graph g) {
        Random r = new Random();
        int n = g.getSize();
        // Adds 4*n random edges.
        for (int i = 0; i < 4*n; i++) {
            int to = r.nextInt(n);
            int from = r.nextInt(n);
            while (to == from) {
                from = r.nextInt(n);
            }
            double weight = randomWeight();
            g.addEdge(from, to, weight);
        }
        return g;
    }

    // Returns a random connected, undirected graph to test Prim's algorithm. Runtime could be improved with a well-implemented set,
    //      but I chose this algorithm because it was easy to implement and deterministic.
    //      Takes in an empty graph and populates it.
    public static Graph randUndConGraph(Graph g) {
        Random r = new Random();
        int n = g.getSize();
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

    // Create a matrix-backed copy of the graph g.
    public static Graph copyToMatrix(Graph g) {
        Graph copy = new MatrixGraph(g.getSize(), !g.isDirected());
        for (int i = 0; i < g.getSize(); i++) {
            MyList<Edge> curOutEdges = g.outEdges(i);
            for (int j = 0; j < curOutEdges.size(); j++) {
                copy.addEdge(curOutEdges.get(j));
            }
        }
        return copy;
    }

    // Create a lists-backed copy of the graph g.
    public static Graph copyToLists(Graph g) {
        Graph copy = new ListGraph(g.getSize(), !g.isDirected());
        for (int i = 0; i < g.getSize(); i++) {
            MyList<Edge> curOutEdges = g.outEdges(i);
            for (int j = 0; j < curOutEdges.size(); j++) {
                copy.addEdge(curOutEdges.get(j));
            }
        }
        return copy;
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