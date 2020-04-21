public class Edge implements Comparable<Edge> {

    private double weight;
    private int fromVertex;
    private int toVertex;

    public Edge(int from, int to, double w) {
        this.toVertex = to;
        this.fromVertex = from;
        this.weight = w;
    }

    public boolean equals(Edge e) {
        if (e.getTo() == toVertex && e.getFrom() == fromVertex && e.getWeight() == weight) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = "Edge from " + fromVertex + " to " + toVertex + " with weight " + weight;
        return str;
    }

    public int getTo() {return this.toVertex;}
    public int getFrom() {return this.fromVertex;}
    public double getWeight() {return this.weight;}

    public int compareTo(Edge other) {
        if (other.getWeight() < weight) {
            return 1;
        } else if (other.getWeight() > weight) {
            return -1;
        }
        return 0;
    }
}