public class Run {
    public static void main(String[] args) {

        // Set the size of graph you would like to test here.
        //      Warning: increasing beyond 10000 will exhaust allocated memory
        int testSize = 10000;

        // Create random graphs for Dijkstra's
        Graph dijkstraMatrix = new MatrixGraph(testSize);
        Graph.randDirUncGraph(dijkstraMatrix);
        System.out.println();
        Graph dijkstraLists = Graph.copyToLists(dijkstraMatrix);

        // Tests
        System.out.println("Running Dijkstra's on MatrixGraph...");
        long start = System.currentTimeMillis();
        VertexPath[] table = dijkstraMatrix.dijkstra(0);
        long stop = System.currentTimeMillis();
        System.out.println("Took " + (stop-start) + " milliseconds.");
        System.out.println();
        dijkstraMatrix = new MatrixGraph(0);

        System.out.println("Running Dijkstra's on ListGraph...");
        start = System.currentTimeMillis();
        table = dijkstraLists.dijkstra(0);
        stop = System.currentTimeMillis();
        System.out.println("Took " + (stop-start) + " milliseconds.");
        System.out.println();
        dijkstraLists = new ListGraph(0);

        // Create random graphs for Prim's
        Graph primMatrix = new MatrixGraph(testSize, true);
        Graph.randUndConGraph(primMatrix);
        Graph primLists = Graph.copyToLists(primMatrix);

        System.out.println("Running Prim's on MatrixGraph...");
        start = System.currentTimeMillis();
        Graph primRes = primMatrix.prim(0);
        stop = System.currentTimeMillis();
        System.out.println("Took " + (stop-start) + " milliseconds.");
        System.out.println();
        primMatrix = new MatrixGraph(0);

        System.out.println("Running Prim's on ListGraph...");
        start = System.currentTimeMillis();
        primRes = primLists.prim(0);
        stop = System.currentTimeMillis();
        System.out.println("Took " + (stop-start) + " milliseconds.");
        System.out.println();
    }
}