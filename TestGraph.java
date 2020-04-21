public class TestGraph {
    public static void main(String[] args) {
        Graph g = new MatrixGraph(8);
        g.addEdge(0,1,9);
        g.addEdge(0,2,6);
        g.addEdge(1,2,5);
        g.addEdge(1,5,7);
        g.addEdge(2,0,1);
        g.addEdge(2,1,5);
        g.addEdge(2,6,1);
        g.addEdge(4,2,2);
        g.addEdge(4,7,2);
        g.addEdge(5,3,9);
        g.addEdge(6,4,5);
        g.addEdge(6,7,7);
        g.addEdge(7,5,1);
        g.addEdge(7,6,4);
        VertexPath[] dijkstraTable = g.dijkstra(2);
        for (int i = 0; i < dijkstraTable.length; i++) {
            System.out.println(dijkstraTable[i]);
        }

    }
}