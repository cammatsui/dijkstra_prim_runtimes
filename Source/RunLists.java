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
        dijkstraTest.writeToFile("Logs/listDijkstraGraph.txt");

        // Dijkstra's
        System.out.println("Running Dijkstra's on ListGraph...");
        long start = System.currentTimeMillis();
        DijkstraTable table = dijkstraTest.dijkstra(0);
        long stop = System.currentTimeMillis();
        table.writeToFile("Logs/listDijkstraTable.txt");
        long dijkstraRuntime = stop-start;
        System.out.println("Took " + dijkstraRuntime + " milliseconds.");

        // Create random graph to test Prim's algorithm
        Graph primTest = new ListGraph(testSize, true);
        Graph.randUndConGraph(primTest);
        primTest.writeToFile("Logs/listPrimGraph.txt");

        // Prim's
        System.out.println("Running Prim's on ListGraph...");
        start = System.currentTimeMillis();
        Graph mst = primTest.prim(0);
        stop = System.currentTimeMillis();
        mst.writeToFile("Logs/listPrimTree.txt");
        long primRunTime = stop-start;
        System.out.println("Took " + primRunTime + " milliseconds.");

        // Record runtimes.
        try{ 
            File f = new File("Logs/RunListLog.txt");
            f.createNewFile();
            FileWriter writer = new FileWriter("Logs/RunListLog.txt");
            writer.write("Dijkstra's runtime on list graph: " + dijkstraRuntime + " milliseconds\n");
            writer.write("Prim's runtime on list graph: " + primRunTime + " milliseconds\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("IO Error writing RunListLog.txt");
            e.printStackTrace();
        }
    }
}