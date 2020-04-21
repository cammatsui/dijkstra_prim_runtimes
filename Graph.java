public abstract class Graph {
    public abstract void addEdge(int i, int j, double w);
    public abstract void removeEdge(int i, int j);
    public abstract boolean hasEdge(int i, int j);
    public abstract double getWeight(int i, int j);
    public abstract MyList<Edge> inEdges(int i);
    public abstract MyList<Edge> outEdges(int i);
    public abstract int getSize();

    public VertexPath[] dijkstra(int startNode) {
        // "Routing" table.
        VertexPath[] dijkstraTable = new VertexPath[this.getSize()];
        
        // List of unvisited vertex. A vertex is visited once all of its neighbors have been examined.
        MyList<VertexPath> unvisited = new MyList<>();
        
        // Initialize our table
        for (int i = 0; i < this.getSize(); i++) {
            VertexPath thisVertex = new VertexPath(i);
            // Set initial node's path length to 0.
            if (i == startNode) thisVertex.setPath(0);

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

        return dijkstraTable;
    }
}