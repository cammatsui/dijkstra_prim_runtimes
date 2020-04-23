// Class represents a vertex, its shortest path to the starting vertex, and the previous vertex in this path during Dijkstra's algorithm.
public class VertexPath implements Comparable<VertexPath> {

    private int vertex;
    private int prev;
    private double shortestPath;

    public VertexPath(int vertex) {
        this.vertex = vertex;
        this.shortestPath = Double.POSITIVE_INFINITY;
    }

    // Getters and setters for fields that need to be accessed. O(11).
    public int getVertex() {return vertex;}
    public int getPrev() {return prev;}
    public double getPath() {return shortestPath;}
    public void setPrev(int newPrev) {this.prev = newPrev;}
    public void setPath(double newPath) {this.shortestPath = newPath;}

    // Allows us to find the vertex with the shortest current path during the algorithm. O(1).
    @Override
    public int compareTo(VertexPath other) {
        if (other.getPath() < shortestPath) {
            return 1;
        } else if (other.getPath() > shortestPath) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {return vertex + " | " + shortestPath + " | " + prev;}
    
}