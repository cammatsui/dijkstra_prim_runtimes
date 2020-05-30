import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

// A representation of the "routing table" used in Dijkstra's and Prim's algorithms.
public class VertexTable {

    private VertexPath[] table;
    private int size;

    public VertexTable(int size) {
        this.size = size;
        this.table = new VertexPath[size];
    }

    // Get and set elements in the table.
    public void set(int i, VertexPath v) {table[i]=v;}
    public VertexPath get(int i) {return table[i];}
    public int getSize() {return size;}

    // Print the table.
    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(table[i]);
        }
    }

    // Print the table to a file.
    public void writeToFile(String fileName) {
        try {
            File f = new File(fileName);
            f.createNewFile();
            FileWriter writer = new FileWriter(fileName);
            writer.write("Node | Distance To | Previous Node in Path (-1 is unreachable, or starting node)\n");
            for (int i = 0; i < this.size; i++) {
                writer.write(table[i].toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("IO Error writing " + fileName);
            e.printStackTrace();
        }
    }

}