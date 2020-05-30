// Each Edge object represents an edge in a graph.
//      These objects are used directly in an adjacency-lists graph implementation,
//      and indirectly in the inEdges() and outEdges() functions of an adjacency-matrix
//      graph implementation.
public class Edge implements Comparable<Edge> {

    private double weight;
    private int fromVertex;
    private int toVertex;

    public Edge(int from, int to, double w) {
        this.toVertex = to;
        this.fromVertex = from;
        this.weight = w;
    }

    // Allows us to see if two edges are the same. O(1).
    public boolean equals(Edge e) {
        if (e.getTo() == toVertex && e.getFrom() == fromVertex && e.getWeight() == weight) return true;
        return false;
    }

    public String toString() {return "Edge from " + fromVertex + " to " + toVertex + " with weight " + weight;}

    // Getters and setters. O(1).
    public int getTo() {return this.toVertex;}
    public int getFrom() {return this.fromVertex;}
    public double getWeight() {return this.weight;}

    // Allows us to compare edges directly by their weights for use in the min heap. O(1).
    @Override
    public int compareTo(Edge other) {
        if (other.getWeight() < weight) return 1;
        else if (other.getWeight() > weight) return -1;
        else return 0;
    }
}