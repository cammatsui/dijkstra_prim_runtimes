import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class RunLists {
    public static void main(String[] args) {
        // Set the size of graph you would like to test here.
        //      Warning: increasing beyond 7500 will exhaust allocated memory
        int testSize = 7500;

        // Create random graph to test Dijkstra's algorithm
        Graph dijkstraTest = new ListGraph(testSize);
        Graph.randDirUncGraph(dijkstraTest);
        dijkstraTest.writeToFile("logs/listDijkstraGraph.txt");

        // Dijkstra's
        System.out.println("Running Dijkstra's on ListGraph...");
        long start = System.currentTimeMillis();
        VertexTable dijkstraTable = dijkstraTest.dijkstra(0);
        long stop = System.currentTimeMillis();
        dijkstraTable.writeToFile("logs/listDijkstraTable.txt");
        long dijkstraRuntime = stop-start;
        System.out.println("Took " + dijkstraRuntime + " milliseconds.");

        // Create random graph to test Prim's algorithm
        Graph primTest = new ListGraph(testSize, true);
        Graph.randUndConGraph(primTest);
        primTest.writeToFile("logs/listPrimGraph.txt");

        // Prim's
        System.out.println("Running Prim's on ListGraph...");
        start = System.currentTimeMillis();
        VertexTable primTable = primTest.prim(0);
        stop = System.currentTimeMillis();
        primTable.writeToFile("logs/listPrimTable.txt");
        long primRunTime = stop-start;
        System.out.println("Took " + primRunTime + " milliseconds.");

        // Record runtimes.
        try{ 
            File f = new File("logs/RunListLog.txt");
            f.createNewFile();
            FileWriter writer = new FileWriter("logs/RunListLog.txt");
            writer.write("Dijkstra's runtime on list graph: " + dijkstraRuntime + " milliseconds\n");
            writer.write("Prim's runtime on list graph: " + primRunTime + " milliseconds\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("IO Error writing RunListLog.txt");
            e.printStackTrace();
        }
    }
}